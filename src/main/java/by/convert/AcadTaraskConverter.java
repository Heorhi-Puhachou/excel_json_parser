package by.convert;

import java.util.ArrayList;
import java.util.Arrays;

public class AcadTaraskConverter extends BaseConverter {

    public String convert(String acad) {

        if (acad == null || acad.isEmpty()) {
            return acad;
        }
        return chekCoran(chekI(chekMZ(acad)));

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

    private String chekI(String in) {
        // Arrays.stream(MiakkiHalosny.values()).forEach(mg -> in.replace(mg.value+" і ", mg.value+" й "));
        //TODO add zverdija galosnija

        return in
                .replace("е і ", "е й ")
                .replace("ё і ", "ё й ")
                .replace("і і ", "і й ")
                .replace("я і ", "я й ")
                .replace("ю і ", "ю й ")
                .replace("э і ", "э й ")
                .replace("о і ", "о й ")
                .replace("ы і ", "ы й ")
                .replace("а і ", "а й ")
                .replace("у і ", "у й ");
    }

    private String chekCoran(String in) {
        return in
                .replace("клас", "кляс")
                .replace("логік", "лёгік")
                .replace("лагіч", "лягіч")
                .replace("анверт", "анвэрт")
                .replace("Каментарый", "Камэнтар")
                .replace("каментарый", "камэнтар")
                .replace("ласар", "лясар");
    }
}
