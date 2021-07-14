package com.asia.ph.core.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoucherDTO implements Serializable {

    private static final long serialVersionUID = -5797863648465315747L;

    private String code;

    private double discount;

    private LocalDate expiry;

}
