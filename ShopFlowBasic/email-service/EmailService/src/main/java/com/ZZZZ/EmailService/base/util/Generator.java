package com.ZZZZ.EmailService.base.util;

import java.text.DecimalFormat;
import java.util.Random;

public class Generator {
    public static String generateOTPCode() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
