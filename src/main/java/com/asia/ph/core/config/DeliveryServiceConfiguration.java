package com.asia.ph.core.config;

import com.asia.ph.core.client.VoucherClient;
import com.asia.ph.core.rules.*;
import com.asia.ph.core.service.DeliveryCostCalculatorService;
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
    List<DeliveryCostRule> deliveryCostRules(ParcelProperties parcelProperties, DeliveryCostProperties deliveryCostProperties) {
        List<DeliveryCostRule> deliveryCostRules = new LinkedList<>();
        deliveryCostRules.add(new OverweightParcelRule(parcelProperties));
        deliveryCostRules.add(new HeavyParcelRule(parcelProperties, deliveryCostProperties));
        deliveryCostRules.add(new SmallParcelRule(parcelProperties, deliveryCostProperties));
        deliveryCostRules.add(new MediumParcelRule(parcelProperties, deliveryCostProperties));
        deliveryCostRules.add(new LargeParcelRule(deliveryCostProperties));

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

    @Bean
    DeliveryCostCalculatorService deliveryCostCalculatorService(List<DeliveryCostRule> deliveryCostRules, VoucherClient voucherClient) {
        return new DeliveryCostCalculatorService(deliveryCostRules, voucherClient);
    }

}
