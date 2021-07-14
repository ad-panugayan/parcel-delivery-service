package com.asia.ph.core.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParcelDTO implements Serializable {

    private static final long serialVersionUID = 182384137676371461L;

    private double weight;

    private double height;

    private double width;

    private double length;

}
