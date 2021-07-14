package com.asia.ph.core.service;

import com.asia.ph.core.client.VoucherClient;
import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.dto.VoucherDTO;
import com.asia.ph.core.rules.DeliveryCostRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class DeliveryCostCalculatorService {

    private final List<DeliveryCostRule> deliveryCostRules;

    private final VoucherClient voucherClient;

    public Double computeDeliveryCost(ParcelDTO parcelDTO, String voucherCode) {
        double deliveryCost = classifyAndComputeDelivery(parcelDTO);

        if (StringUtils.isEmpty(voucherCode)) {
            return deliveryCost;
        }

        return computeVoucherDiscount(deliveryCost, voucherCode);
    }

    private double classifyAndComputeDelivery(ParcelDTO parcelDTO) {
        DeliveryCostRule rule = deliveryCostRules.stream()
                .filter(deliveryCostRule -> deliveryCostRule.conditionIsMet(parcelDTO))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Unable to compute delivery cost"));

        return rule.compute(parcelDTO);
    }

    private double computeVoucherDiscount(double deliveryCost, String voucherCode) {
        VoucherDTO voucherDTO = voucherClient.getVoucher(voucherCode);

        if (Objects.nonNull(voucherDTO) && LocalDate.now().isBefore(voucherDTO.getExpiry())) {
            return deliveryCost * voucherDTO.getDiscount() >= 0 ? deliveryCost - voucherDTO.getDiscount() : 0;
        }

        return deliveryCost;
    }

}
