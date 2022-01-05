package by.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilCheck {

    public static boolean isGalosny(String symbol) {
        String nonDelimiterPattern = "[аяоёэеуюыі]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isGalosny(char symbol) {
        return isGalosny("" + symbol);
    }

    public static boolean isEngWord(String word) {
        String nonDelimiterPattern = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    public static boolean isEngSymbol(char symbol) {
        return isEngWord("" + symbol);
    }

    public static boolean isNumber(char symbol) {
        String nonDelimiterPattern = "[\\d]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher("" + symbol);
        return matcher.matches();
    }

    public static boolean isApostraf(String symbol) {
        return "'".equals(symbol);
    }

    public static boolean isApostraf(char symbol) {
        return isApostraf("" + symbol);
    }

    public static boolean isMiakkiGalosny(String symbol) {
        String nonDelimiterPattern = "[яёеюі]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isMiakkiGalosny(char symbol) {
        return isMiakkiGalosny("" + symbol);
    }
}
