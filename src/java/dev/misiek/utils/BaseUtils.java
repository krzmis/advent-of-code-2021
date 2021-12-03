package dev.misiek.utils;

public abstract class BaseUtils {

    public static int baseToDecimal(int base, String number) {
        int result = 0;

        for (int power = 0; power < number.length(); power++) {
            String character = Character.toString(StringUtils.reversString(number).charAt(power));
            int asd = Integer.parseInt(character) * (int) Math.pow(base, power);
            result += asd;
        }

        return result;
    }
}