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
        return switch (character.toLowerCase(Locale.ROOT)) {
            case "a" -> 10;
            case "b" -> 11;
            case "c" -> 12;
            case "d" -> 13;
            case "e" -> 14;
            case "f" -> 15;
            default -> Integer.parseInt(character);
        };
    }
}