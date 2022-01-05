package by.convert;

import by.parser.ParsedElement;
import by.parser.Parser;
import by.util.MiakkiHalosny;
import by.util.MiakkiZycnyy;
import by.util.WordCase;

import java.util.ArrayList;
import java.util.Arrays;

import static by.util.StringUtilCheck.isEngWord;
import static by.util.StringUtilCheck.isGalosny;
import static by.util.StringUtilGet.findFirstGalosny;
import static by.util.StringUtilTransform.transformCase;

public class NarkamTaraskConverter extends BaseConverter {

    public static void main(String... args) {
        NarkamTaraskConverter converter = new NarkamTaraskConverter();
        System.out.println(converter.convert("Яна і ён убачылі свет! не ведаю не трэба не той без той..."));
    }

    private Parser parser;

    public NarkamTaraskConverter() {
        this.parser = new Parser();
    }

    public String convert(String narkam) {

        if (narkam == null || narkam.isEmpty()) {
            return narkam;
        }

        ArrayList<ParsedElement> elements = parser.parse(narkam);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < elements.size(); i++) {

            ParsedElement current = elements.get(i);

            if (isEngWord(current.getOriginalWord()) || current.getWordCase() == WordCase.OTHER) {
                result.append(current.getDelimiter()).append(current.getOriginalWord());
            } else {
                ParsedElement prev = null;
                ParsedElement next = null;
                if (i > 0) {
                    prev = elements.get(i - 1);
                }
                if (i < elements.size() - 1) {
                    next = elements.get(i + 1);
                }
                result.append(elements.get(i).getDelimiter()).append(convertElement(prev, current, next));
            }
        }


        return result.toString();

    }

    private String convertElement(ParsedElement prev, ParsedElement current, ParsedElement next) {
        String convertedValue = checkI(prev, current.getWord(), current.getDelimiter());
        convertedValue = checkNe(convertedValue, next);
        convertedValue = checkBez(convertedValue, next);
        convertedValue = chekCoran(chekMZ(convertedValue));
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        return convertedValue;
    }

    private String checkI(ParsedElement prev, String current, String delimiter) {
        if (prev != null && current.equals("і") && delimiter.equals(" ")) {
            String lastPrevSymbol = getLastSymbol(prev.getWord());
            if (isGalosny(lastPrevSymbol)) {
                return "й";
            }
        }
        return current;
    }

    private String checkNe(String current, ParsedElement next) {
        if (current.equals("не")
                && next != null
                && next.getDelimiter().equals(" ")
                && pershySkladPadNaciskam(next.getWord())) {
            return "ня";
        }

        return current;
    }

    private String checkBez(String current, ParsedElement next) {
        if (current.equals("без")
                && next != null
                && next.getDelimiter().equals(" ")
                && pershySkladPadNaciskam(next.getWord())) {
            return "бяз";
        }

        return current;
    }


    private String chekMZ(String in) {
        ArrayList<String> zmyakchateli = new ArrayList<>();
        zmyakchateli.add("ь");

        Arrays.stream(MiakkiHalosny.values()).forEach(val -> zmyakchateli.add(val.value));

        for (int i = 0; i < MiakkiZycnyy.values().length; i++) {
            for (int j = 0; j < MiakkiZycnyy.values().length; j++) {
                for (int z = 0; z < zmyakchateli.size(); z++) {
                    String narkam = MiakkiZycnyy.values()[i].value + MiakkiZycnyy.values()[j].value + zmyakchateli.get(z);
                    String tarask = MiakkiZycnyy.values()[i].value + "ь" + MiakkiZycnyy.values()[j].value + zmyakchateli.get(z);
                    in = in.replace(narkam, tarask);
                }
            }
        }
        return in;
    }

    private String chekCoran(String in) {
        return in
                .replace("анверт", "анвэрт")
                .replace("аргумент", "аргумэнт")
                .replace("артапед", "артапэд")
                .replace("арфаграф", "артаграф")
                .replace("арыстоцель", "арыстотэль")
                .replace("валанцёр", "валантэр")
                .replace("гаус", "гаўс")
                .replace("Генры", "Гэнры")
                .replace("донья", "доньня")
                .replace("еўр", "эўр")
                .replace("каментарый", "камэнтар")
                .replace("клас", "кляс")
                .replace("клуб", "клюб")
                .replace("лагіч", "лягіч")
                .replace("лампада", "лямпада")
                .replace("логік", "лёгік")
                .replace("мекка", "мэка")
                .replace("мільянер", "мільянэр")
                .replace("музей", "музэй")
                .replace("мушкіцёр", "мушкітэр")
                .replace("саліцёр", "слітэр")
                .replace("фунікулёр", "фунікулер")
                .replace("фальклор", "фальклёр")
                .replace("гласар", "глясар")
                .replace("шоу", "шоў");
    }

    private String firstLetterToUpperCase(String word) {
        String low = word.toLowerCase();
        String first = low.substring(0, 1);
        return first.toUpperCase() + low.substring(1);
    }

    private String getLastSymbol(String input) {
        return input.substring(input.length() - 1);
    }


    private boolean pershySkladPadNaciskam(String word) {
        return word.equals("бачу")
                || word.equals("назвы")
                || word.equals("будзе")
                || word.equals("трэба")
                || word.equals("ведаю")
                || "о".equals(findFirstGalosny(word));
    }


}
