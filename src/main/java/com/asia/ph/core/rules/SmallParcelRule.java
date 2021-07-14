package com.asia.ph.core.rules;

import com.asia.ph.core.config.properties.DeliveryCostProperties;
import com.asia.ph.core.config.properties.ParcelProperties;
import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.service.MoneyConverter;
import com.asia.ph.core.util.DeliveryCostCalculator;
import com.asia.ph.core.util.VolumeCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.money.MonetaryAmount;

@Slf4j
@RequiredArgsConstructor
public class SmallParcelRule implements DeliveryCostRule {

    private final ParcelProperties parcelProperties;

    private final DeliveryCostProperties deliveryCostProperties;

    private final MoneyConverter moneyConverter;

    @Override
    public boolean conditionIsMet(ParcelDTO parcelDTO) {
        return VolumeCalculator.calculate(parcelDTO.getLength(), parcelDTO.getWidth(), parcelDTO.getHeight()) < parcelProperties.getSmall();
    }

    @Override
    public MonetaryAmount compute(ParcelDTO parcelDTO) {
        log.info("Computing delivery cost for small parcel: {}", parcelDTO);

        double volume = VolumeCalculator.calculate(parcelDTO.getLength(), parcelDTO.getWidth(), parcelDTO.getHeight());
        MonetaryAmount basePrice = moneyConverter.convertDoubleToMoney(deliveryCostProperties.getSmallParcelBasePrice());

        return DeliveryCostCalculator.calculateByPriceAndVolume(basePrice, volume);
    }

}
