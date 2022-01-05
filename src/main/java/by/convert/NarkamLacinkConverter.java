package by.convert;

import by.parser.ParsedElement;
import by.parser.Parser;
import by.parser.WordCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NarkamLacinkConverter extends BaseConverter {

    private Parser parser;
    private HashMap<String, String> pairs;
    private HashMap<String, String> pairsMiakkija;

    public NarkamLacinkConverter() {

        this.parser = new Parser();
        pairs = new HashMap<>();
        pairs.put("а", "a");
        pairs.put("б", "b");
        pairs.put("в", "v");
        pairs.put("г", "h");
        pairs.put("д", "d");
        pairs.put("е", "je");
        pairs.put("ё", "jo");
        pairs.put("ж", "z");
        pairs.put("з", "ź");
        pairs.put("і", "i");
        pairs.put("й", "j");
        pairs.put("к", "k");
        pairs.put("Л", "L");
        pairs.put("л", "l");
        pairs.put("м", "m");
        pairs.put("н", "n");
        pairs.put("о", "o");
        pairs.put("п", "p");
        pairs.put("р", "r");
        pairs.put("с", "s");
        pairs.put("т", "t");
        pairs.put("у", "u");
        pairs.put("Ў", "Ŭ");
        pairs.put("Ф", "F");
        pairs.put("ф", "f");
        pairs.put("х", "ch");
        pairs.put("ц", "c");
        pairs.put("ч", "č");
        pairs.put("ш", "š");
        pairs.put("ы", "y");
        pairs.put("ь", "");
        pairs.put("э", "e");
        pairs.put("ю", "ju");
        pairs.put("я", "ja");

        pairsMiakkija = new HashMap<>();
        pairsMiakkija.put("l", "ĺ");
        pairsMiakkija.put("n", "ń");
        pairsMiakkija.put("c", "ć");
        pairsMiakkija.put("s", "ś");
        pairsMiakkija.put("z", "ź");
    }

    public static void main(String... args) {
        NarkamLacinkConverter converter = new NarkamLacinkConverter();
        System.out.println(converter.convert("актываваць"));
    }

    public String convert(String acad) {

        if (acad == null || acad.isEmpty()) {
            return acad;
        }

        String tarask = (new NarkamTaraskConverter()).convert(acad);


        ArrayList<ParsedElement> elements = parser.parse(tarask);

        StringBuilder result = new StringBuilder();

        for (ParsedElement current : elements) {
            if (isEngWord(current.getOriginalWord())) {
                result.append(current.getDelimiter()).append(current.getOriginalWord());
            } else {
                result.append(current.getDelimiter()).append(convertElement(current));
            }
        }


        return result.toString();
    }

    private String convertElement(ParsedElement current) {


        String convertedValue = advancedReplace(current.getWord());

        if (current.getWordCase() == WordCase.FIRST_LETTER_UPPER) {
            convertedValue = firstLetterToUpperCase(convertedValue);
        }
        if (current.getWordCase() == WordCase.ALL_LETTERS_UPPER) {
            convertedValue = convertedValue.toUpperCase();
        }
        if (current.getWordCase() == WordCase.ALL_LETTERS_LOWER) {
            convertedValue = convertedValue.toLowerCase();
        }
        return convertedValue;
    }


    private String advancedReplace(String word) {
        String result = "";

        char[] chars = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (isNumber(chars[i])) {
                result = result + chars[i];
            } else {
                if (i > 0 && ("" + chars[i]).equals("ь")) {
                    result = replaceLastToMiakki(result);
                }

                String symbol = pairs.get("" + chars[i]);
                if (i > 0 && isMiakkiGalosny("" + chars[i]) && !isGalosny("" + chars[i - 1])) {
                    symbol = symbol.replace("j", "i");
                }
                result = result + symbol;
            }
        }

        return result;
    }

    private boolean isEngWord(String word) {
        String nonDelimiterPattern = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    private String firstLetterToUpperCase(String word) {
        String low = word.toLowerCase();
        String first = low.substring(0, 1);
        return first.toUpperCase() + low.substring(1);
    }

    private boolean isMiakkiGalosny(String symbol) {
        String nonDelimiterPattern = "[яёеюі]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    private boolean isGalosny(String symbol) {
        String nonDelimiterPattern = "[аяоёэеуюыі]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    private String replaceLastToMiakki(String word) {
        if (word.length() == 1) {
            return pairsMiakkija.get(word);
        }

        return word.substring(0, word.length() - 1) + pairsMiakkija.get(getLastSymbol(word));
    }

    private String getLastSymbol(String input) {
        return input.substring(input.length() - 1);
    }

    private boolean isNumber(char symbol) {
        String nonDelimiterPattern = "[\\d]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher("" + symbol);
        return matcher.matches();
    }

}
