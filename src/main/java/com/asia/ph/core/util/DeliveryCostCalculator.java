package com.asia.ph.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryCostCalculator {

    public static double calculateByPriceAndVolume(double basePrice, double volume) {
        return basePrice * volume;
    }

    public static double calculateByPriceAndWeight(double basePrice, double weight) {
        return basePrice * weight;
    }

}
