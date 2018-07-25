package com.gfbdev.utils;

import java.util.Random;

public class StringUtils {

    public static String generateRandomCode() {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz123456789";
        final int N = alphabet.length();
        StringBuilder result = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < 6; i++) {
            result.append(alphabet.charAt(r.nextInt(N)));
        }
        return result.toString();
    }
}
