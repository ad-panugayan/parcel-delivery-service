package com.asia.ph.core.rules;

import com.asia.ph.core.config.properties.DeliveryCostProperties;
import com.asia.ph.core.config.properties.ParcelProperties;
import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.service.MoneyConverter;
import com.asia.ph.core.util.DeliveryCostCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.money.MonetaryAmount;

@Slf4j
@RequiredArgsConstructor
public class HeavyParcelRule implements DeliveryCostRule {

    private final ParcelProperties parcelProperties;

    private final DeliveryCostProperties deliveryCostProperties;

    private final MoneyConverter moneyConverter;

    @Override
    public boolean conditionIsMet(ParcelDTO parcelDTO) {
        return parcelDTO.getWeight() > parcelProperties.getHeavyweight();
    }

    @Override
    public MonetaryAmount compute(ParcelDTO parcelDTO) {
        log.info("Computing delivery cost for heavy parcel: {}", parcelDTO);

        MonetaryAmount basePrice = moneyConverter.convertDoubleToMoney(deliveryCostProperties.getHeavyParcelBasePrice());

        return DeliveryCostCalculator.calculateByPriceAndWeight(basePrice, parcelDTO.getWeight());
    }

}
