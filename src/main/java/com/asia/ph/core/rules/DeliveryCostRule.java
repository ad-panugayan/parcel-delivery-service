package com.asia.ph.core.rules;

import com.asia.ph.core.dto.ParcelDTO;

import javax.money.MonetaryAmount;

public interface DeliveryCostRule {
    boolean conditionIsMet(ParcelDTO parcelDTO);
    MonetaryAmount compute(ParcelDTO parcelDTO);
}
