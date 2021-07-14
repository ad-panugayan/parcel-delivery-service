package com.asia.ph.core.rules;

import com.asia.ph.core.config.properties.ParcelProperties;
import com.asia.ph.core.dto.ParcelDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.money.MonetaryAmount;

@Slf4j
@RequiredArgsConstructor
public class OverweightParcelRule implements DeliveryCostRule {

    private final ParcelProperties parcelProperties;

    @Override
    public boolean conditionIsMet(ParcelDTO parcelDTO) {
        return parcelDTO.getWeight() > parcelProperties.getOverweight();
    }

    @Override
    public MonetaryAmount compute(ParcelDTO parcelDTO) {
        log.error("Parcel is overweight: {}", parcelDTO);
        throw new UnsupportedOperationException("Parcel is overweight");
    }

}
