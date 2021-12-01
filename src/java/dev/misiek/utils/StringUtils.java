package dev.misiek.utils;

public abstract class StringUtils {

    public static String reversString(String str) {
        var reversed = new StringBuilder();
        str.chars().forEach(c -> reversed.insert(0, Character.toString(c)));
        
        return reversed.toString();
    }
}