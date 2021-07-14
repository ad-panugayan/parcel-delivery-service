package com.asia.ph.core.rules;

import com.asia.ph.core.dto.ParcelDTO;

public interface DeliveryCostRule {
    boolean conditionIsMet(ParcelDTO parcelDTO);
    double compute(ParcelDTO parcelDTO);
}
