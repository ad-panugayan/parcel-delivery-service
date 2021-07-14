package com.asia.ph.core.rules;

import com.asia.ph.core.config.DeliveryCostProperties;
import com.asia.ph.core.config.ParcelProperties;
import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.util.DeliveryCostCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeavyParcelRule implements DeliveryCostRule {

    private final ParcelProperties parcelProperties;

    private final DeliveryCostProperties deliveryCostProperties;

    @Override
    public boolean conditionIsMet(ParcelDTO parcelDTO) {
        return parcelDTO.getWeight() > parcelProperties.getHeavyweight();
    }

    @Override
    public double compute(ParcelDTO parcelDTO) {
        log.info("Computing delivery cost for heavy parcel: {}", parcelDTO);
        return DeliveryCostCalculator.calculateByPriceAndWeight(deliveryCostProperties.getHeavyParcelBasePrice(), parcelDTO.getWeight());
    }

}
