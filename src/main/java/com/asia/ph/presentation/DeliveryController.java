package com.asia.ph.presentation;

import com.asia.ph.core.dto.ParcelDTO;
import com.asia.ph.core.service.DeliveryCostCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.money.MonetaryAmount;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryCostCalculatorService deliveryCostCalculatorService;

    @PostMapping("/delivery/calculate-cost")
    public ResponseEntity<MonetaryAmount> calculateDeliveryCost(@RequestBody ParcelDTO parcelDTO,
                                                                @RequestParam(value = "voucherCode", required = false) String voucherCode) {
        log.info("Calculate delivery cost for parcel: {}, voucher: {}", parcelDTO, voucherCode);

        MonetaryAmount deliveryCost = deliveryCostCalculatorService.computeDeliveryCost(parcelDTO, voucherCode);

        log.info("Delivery cost for parcel: {}, voucher: {}, cost: {}", parcelDTO, voucherCode, deliveryCost);

        return ResponseEntity.ok(deliveryCost);
    }

}
