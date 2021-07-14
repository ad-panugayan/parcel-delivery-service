package com.asia.ph.infra;

import com.asia.ph.core.client.VoucherClient;
import com.asia.ph.core.config.VoucherClientProperties;
import com.asia.ph.core.dto.VoucherDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
public class MyntVoucherClient implements VoucherClient {

    private final VoucherClientProperties voucherClientProperties;

    private final RestTemplate restTemplate;

    @Override
    public VoucherDTO getVoucher(String voucherCode) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(voucherClientProperties.getUrl().concat(voucherCode))
                    .queryParam("key", "apikey");

            VoucherDTO voucherDTO = restTemplate.getForObject(builder.toUriString(), VoucherDTO.class);
            log.info("Received voucher: {}", voucherDTO);

            return voucherDTO;
        } catch (RestClientException e) {
            log.error("Error encountered getting voucher. Error: {}", e.getMessage());
            return null;
        }
    }

}
