package com.asia.ph.core.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "voucher.client")
@Getter
@Setter
public class VoucherClientProperties {

    private String url;

}
