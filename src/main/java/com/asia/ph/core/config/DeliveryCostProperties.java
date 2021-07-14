package com.asia.ph.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "delivery.cost")
@Getter
@Setter
public class DeliveryCostProperties {

    private double heavyParcelBasePrice;

    private double smallParcelBasePrice;

    private double mediumParcelBasePrice;

    private double largeParcelBasePrice;

}
