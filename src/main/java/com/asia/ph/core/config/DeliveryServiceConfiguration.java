package com.asia.ph.core.config;

import com.asia.ph.core.client.VoucherClient;
import com.asia.ph.core.config.properties.DeliveryCostProperties;
import com.asia.ph.core.config.properties.ParcelProperties;
import com.asia.ph.core.config.properties.VoucherClientProperties;
import com.asia.ph.core.rules.*;
import com.asia.ph.core.service.MoneyConverter;
import com.asia.ph.infra.MyntVoucherClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class DeliveryServiceConfiguration {

    @Bean
    MoneyConverter moneyConverter(DeliveryCostProperties deliveryCostProperties) {
        return new MoneyConverter(deliveryCostProperties.getCurrency());
    }

    @Bean
    List<DeliveryCostRule> deliveryCostRules(ParcelProperties parcelProperties, DeliveryCostProperties deliveryCostProperties, MoneyConverter moneyConverter) {
        List<DeliveryCostRule> deliveryCostRules = new LinkedList<>();
        deliveryCostRules.add(new OverweightParcelRule(parcelProperties));
        deliveryCostRules.add(new HeavyParcelRule(parcelProperties, deliveryCostProperties, moneyConverter));
        deliveryCostRules.add(new SmallParcelRule(parcelProperties, deliveryCostProperties, moneyConverter));
        deliveryCostRules.add(new MediumParcelRule(parcelProperties, deliveryCostProperties, moneyConverter));
        deliveryCostRules.add(new LargeParcelRule(deliveryCostProperties, moneyConverter));

        return deliveryCostRules;
    }

    @Bean
    VoucherClient voucherClient(VoucherClientProperties voucherClientProperties) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        return new MyntVoucherClient(voucherClientProperties, new RestTemplate(requestFactory));
    }

}
