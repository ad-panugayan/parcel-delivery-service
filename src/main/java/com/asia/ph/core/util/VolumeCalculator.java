package com.asia.ph.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VolumeCalculator {

    public static double calculate(double length, double width, double height) {
        return length * width * height;
    }

}
