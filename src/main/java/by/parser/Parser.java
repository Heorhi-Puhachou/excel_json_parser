package by.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void main(String... args) {
        parse("Прыклад беларукага тэксту! And eng And 1234///... ulіkоvy źаpіs, ulіkоvаhа źаpіsu, ulіkоvym źаpіsie!");
    }

    public static ArrayList<ParsedElement> parse(String text) {
        ArrayList<ParsedElement> result = new ArrayList<>();
        char[] chars = text.toCharArray();
        boolean currentDelimiter = true;
        String currentDelimiterValue = "";
        String currentWordValue = "";
        for (int i = 0; i < text.length(); i++) {
            boolean isDelimiter = !isWordSymbol(chars[i]);


            // Чыталі не-слова й працягваем чытаць не-слова
            if (currentDelimiter && isDelimiter) {
                currentDelimiterValue = currentDelimiterValue + chars[i];

                //Апошні сымбаль тэксту
                if (i == text.length() - 1) {
                    result.add(new ParsedElement(currentDelimiterValue, "", UpperCase.ALL_LETTERS_LOWER));
                }
            }
            // Чыталі не-слова й пачалі чытаць слова
            else if (currentDelimiter) {
                currentDelimiter = false;
                currentWordValue = "" + chars[i];

                //Апошні сымбаль тэксту
                if (i == text.length() - 1) {
                    result.add(new ParsedElement(currentDelimiterValue, currentWordValue, UpperCase.ALL_LETTERS_LOWER));
                }
            }
            // Чыталі слова й працягваем чытаць слова
            else if (!isDelimiter) {
                currentWordValue = currentWordValue + chars[i];

                //Апошні сымбаль тэксту
                if (i == text.length() - 1) {
                    result.add(new ParsedElement(currentDelimiterValue, currentWordValue, UpperCase.ALL_LETTERS_LOWER));
                }
            }
            // Чыталі слова й пачалі чытаць не-слова
            else {
                result.add(new ParsedElement(currentDelimiterValue, currentWordValue, UpperCase.ALL_LETTERS_LOWER));
                currentDelimiter = true;
                currentDelimiterValue = "" + chars[i];

                //Апошні сымбаль тэксту
                if (i == text.length() - 1) {
                    result.add(new ParsedElement(currentDelimiterValue, "", UpperCase.ALL_LETTERS_LOWER));
                }
            }


            System.out.println(chars[i] + " " + isWordSymbol(chars[i]));


        }

        result.forEach(parsedElement -> System.out.println(parsedElement.toString()));

        return result;
    }

    private static boolean isWordSymbol(char symbol) {
        String nonDelimiterPattern = "[\\p{L}\\d]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher("" + symbol);
        return matcher.matches();
    }

}
