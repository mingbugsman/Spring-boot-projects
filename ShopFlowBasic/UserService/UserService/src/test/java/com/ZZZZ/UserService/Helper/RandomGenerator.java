package com.ZZZZ.UserService.Helper;

import java.security.SecureRandom;

public class RandomGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String EMAIL_DOMAINS[] = {"gmail.com", "yahoo.com", "outlook.com"};
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomEmail() {
        String username = generateRandomString(8);
        String domain = EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];
        return username+ "@" + domain;
    }

    public static String generateRandomPassword() {
        return generateRandomString(10);
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
