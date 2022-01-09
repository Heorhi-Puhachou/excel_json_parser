package by.convert.lacink;

import by.convert.BaseConverter;
import by.convert.tarask.NarkamTaraskConverter;
import by.parser.ParsedElement;
import by.parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;

import static by.util.StringUtilCheck.*;
import static by.util.StringUtilGet.getLastSymbol;
import static by.util.StringUtilTransform.transformCase;

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
        pairs.put("з", "z");
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
        pairs.put("ў", "ŭ");
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
        System.out.println(converter.convert("з'езд"));
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
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        return convertedValue;
    }

    private String advancedReplace(String word) {
        String result = "";

        char[] chars = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (isApostraf(chars[i])) {
                //DO NOTHING
            } else if (isNumber(chars[i]) || isEngSymbol(chars[i])) {
                result = result + chars[i];
            } else {
                if (i > 0 && (chars[i] == 'ь')) {
                    result = replaceLastToMiakki(result);
                }

                String symbol = pairs.get("" + chars[i]);
                if (i > 0 && isMiakkiGalosny(chars[i]) && !isGalosny(chars[i - 1]) && !isApostraf(chars[i - 1])) {
                    symbol = symbol.replace("j", "i");
                }
                result = result + symbol;
            }
        }

        return result;
    }

    private String replaceLastToMiakki(String word) {
        if (word.length() == 1) {
            return pairsMiakkija.get(word);
        }
        String replacement = pairsMiakkija.get(getLastSymbol(word)) == null ? getLastSymbol(word) : pairsMiakkija.get(getLastSymbol(word));
        return word.substring(0, word.length() - 1) + replacement;
    }
}
