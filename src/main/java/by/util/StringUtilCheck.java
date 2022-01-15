package by.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.util.StringUtilGet.findFirstVowel;
import static by.util.StringUtilGet.getVowelQuantity;

public class StringUtilCheck {

    public static boolean isWordSymbol(char symbol) {
        String nonDelimiterPattern = "[\\p{L}\\d']";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher("" + symbol);
        return matcher.matches();
    }

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

    public static boolean isNumber(String symbol) {
        String nonDelimiterPattern = "[\\d]+";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isNumber(char symbol) {
        return isNumber("" + symbol);
    }

    public static boolean isApostraf(String symbol) {
        return "'".equals(symbol);
    }

    public static boolean isApostraf(char symbol) {
        return isApostraf("" + symbol);
    }

    public static boolean isMiakkiGalosny(String symbol) {
        String miakkiGalosnyPattern = "[яёеюі]";
        Pattern pattern = Pattern.compile(miakkiGalosnyPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isMiakkiGalosny(char symbol) {
        return isMiakkiGalosny("" + symbol);
    }

    public static boolean isOneVowelInWord(String word) {
        return getVowelQuantity(word) == 1;
    }

    public static boolean pershySkladPadNaciskam(String word) {
        return word.equals("бачу")
                || word.equals("назвы")
                || word.equals("будзе")
                || word.equals("буду")
                || word.equals("трэба")
                || word.equals("ведаю")
                || (isNumber(word) && (word.startsWith("2") || word.startsWith("3")))
                || "о".equals(findFirstVowel(word))
                || isOneVowelInWord(word);
    }
}
