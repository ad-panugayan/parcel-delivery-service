package com.asia.ph.core.rules;

import com.asia.ph.core.config.properties.DeliveryCostProperties;
import com.asia.ph.core.config.properties.ParcelProperties;
import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.util.DeliveryCostCalculator;
import com.asia.ph.core.util.VolumeCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MediumParcelRule implements DeliveryCostRule {

    private final ParcelProperties parcelProperties;

    private final DeliveryCostProperties deliveryCostProperties;

    @Override
    public boolean conditionIsMet(ParcelDTO parcelDTO) {
        return VolumeCalculator.calculate(parcelDTO.getLength(), parcelDTO.getWidth(), parcelDTO.getHeight()) < parcelProperties.getMedium();
    }

    @Override
    public double compute(ParcelDTO parcelDTO) {
        log.info("Computing delivery cost for medium parcel: {}", parcelDTO);
        double volume = VolumeCalculator.calculate(parcelDTO.getLength(), parcelDTO.getWidth(), parcelDTO.getHeight());
        return DeliveryCostCalculator.calculateByPriceAndVolume(deliveryCostProperties.getMediumParcelBasePrice(), volume);
    }

}
