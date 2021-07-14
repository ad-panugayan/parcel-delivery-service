package com.asia.ph.core.client;

import com.asia.ph.core.dto.VoucherDTO;

public interface VoucherClient {
    VoucherDTO getVoucher(String voucherCode);
}
