package br.com.prosperah.api.appcore.utils;

public class GeneralUtils {
    static final int MIN_VALUE = 100000;
    static final int MAX_VALUE = 999999;
    public static int generateRandomSixDigitNumber() {
        return (int) (Math.random() * (MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
    }
}
