package com.asia.ph.core.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "delivery.cost")
@Getter
@Setter
public class DeliveryCostProperties {

    private String currency;

    private double heavyParcelBasePrice;

    private double smallParcelBasePrice;

    private double mediumParcelBasePrice;

    private double largeParcelBasePrice;

}
