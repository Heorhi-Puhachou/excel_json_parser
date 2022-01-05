package by.util;

import static by.util.StringUtilCheck.isGalosny;

public class StringUtilGet {

    public static String findFirstGalosny(String word) {
        char[] chars = word.toCharArray();
        String result = null;

        for (int i = 0; i < word.length(); i++) {
            if (isGalosny("" + chars[i])) {
                return "" + chars[i];
            }
        }
        return result;
    }

    public static String getLastSymbol(String input) {
        return input.substring(input.length() - 1);
    }
}
