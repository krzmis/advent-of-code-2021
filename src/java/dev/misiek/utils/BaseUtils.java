package dev.misiek.utils;

import java.util.Locale;

public abstract class BaseUtils {

    public static int baseToDecimal(int base, String number) {
        int result = 0;

        for (int power = 0; power < number.length(); power++) {
            String character = Character.toString(StringUtils.reversString(number).charAt(power));
            result += checkForHexChar(character) * (int) Math.pow(base, power);
        }

        return result;
    }

    private static int checkForHexChar(String character) {
        return switch (character.toUpperCase(Locale.ROOT)) {
            case "A" -> 10;
            case "B" -> 11;
            case "C" -> 12;
            case "D" -> 13;
            case "E" -> 14;
            case "F" -> 15;
            default -> Integer.parseInt(character);
        };
    }
}