package com.asia.ph.core.service;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

@RequiredArgsConstructor
public class MoneyConverter {

    private final String currency;

    public MonetaryAmount convertDoubleToMoney(double money) {
        return Money.of(money, currency);
    }

}
