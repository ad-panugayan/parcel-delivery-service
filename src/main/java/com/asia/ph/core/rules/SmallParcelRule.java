package com.asia.ph.core.rules;

import com.asia.ph.core.config.DeliveryCostProperties;
import com.asia.ph.core.config.ParcelProperties;
import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.util.DeliveryCostCalculator;
import com.asia.ph.core.util.VolumeCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SmallParcelRule implements DeliveryCostRule {

    private final ParcelProperties parcelProperties;

    private final DeliveryCostProperties deliveryCostProperties;

    @Override
    public boolean conditionIsMet(ParcelDTO parcelDTO) {
        return VolumeCalculator.calculate(parcelDTO.getLength(), parcelDTO.getWidth(), parcelDTO.getHeight()) < parcelProperties.getSmall();
    }

    @Override
    public double compute(ParcelDTO parcelDTO) {
        log.info("Computing delivery cost for small parcel: {}", parcelDTO);
        double volume = VolumeCalculator.calculate(parcelDTO.getLength(), parcelDTO.getWidth(), parcelDTO.getHeight());
        return DeliveryCostCalculator.calculateByPriceAndVolume(deliveryCostProperties.getSmallParcelBasePrice(), volume);
    }

}
