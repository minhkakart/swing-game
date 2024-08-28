package com.minhkakart.swinggame.supports;

public class StringFormatter {
    public static String removeUnnecessarySpace(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }
}
