package com.asia.ph.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryCostCalculator {

    public static MonetaryAmount calculateByPriceAndVolume(MonetaryAmount basePrice, double volume) {
        return basePrice.multiply(volume);
    }

    public static MonetaryAmount calculateByPriceAndWeight(MonetaryAmount basePrice, double weight) {
        return basePrice.multiply(weight);
    }

    public static MonetaryAmount calculateDiscount(MonetaryAmount basePrice, double discount) {
        return basePrice.multiply(discount);
    }

}
