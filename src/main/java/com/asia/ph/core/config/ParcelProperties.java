package com.asia.ph.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "delivery.parcel")
@Getter
@Setter
public class ParcelProperties {

    private double overweight;

    private double heavyweight;

    private double small;

    private double medium;

}
