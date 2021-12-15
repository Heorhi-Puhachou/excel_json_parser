package by.convert;

public class AcadTaraskConverter {

    public static String convert(String acad) {

        if (acad == null || acad.isEmpty()) {
            return acad;
        }
        return chekCoran(chekI(chekMZ(acad)));

    }

    private static String chekMZ(String in) {
        return in
                .replace("сць", "сьць")
                .replace("сці", "сьці")
                .replace("сце", "сьце")
                .replace("сцё", "сьцё")
                .replace("сця", "сьця")
                .replace("сцю", "сьцю")

                .replace("нне", "ньне")
                .replace("нні", "ньні")
                .replace("ннё", "ньнё")
                .replace("ння", "ньня")
                .replace("нню", "ньню")

                .replace("све", "сьве")
                .replace("свё", "сьвё")
                .replace("сві", "сьві")
                .replace("свя", "сьвя")
                .replace("свю", "сьвю")

                .replace("зме", "зьме")
                .replace("змё", "зьмё")
                .replace("змі", "зьмі")
                .replace("змя", "зьмя")
                .replace("змю", "зьмю")

                .replace("дзве", "дзьве")
                .replace("дзвё", "дзьвё")
                .replace("дзві", "дзьві")
                .replace("дзвя", "дзьвя")
                .replace("дзвю", "дзьвю")

                ;
    }

    private static String chekI(String in) {
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

    private static String chekCoran(String in) {
        return in
                .replace("клас", "кляс")
                .replace("логік", "лёгік")
                .replace("лагіч", "лягіч");
    }
}
