package by.convert;

public class AcadLacinkaConverter {

    public static String convert(String acad) {

        if (acad == null || acad.isEmpty()) {
            return acad;
        }
        return acad
                .replace("Б", "B")
                .replace("б", "b")
                .replace("В", "V")
                .replace("в", "v")
                .replace("Г", "H")
                .replace("г", "h")
                .replace("Д", "D")
                .replace("д", "d")
                .replace("Е", "Je")
                .replace("е", "je")
                .replace("Ё", "Jo")
                .replace("ё", "jo")
                .replace("Ж", "Z")
                .replace("ж", "z")
                .replace("З", "Ź")
                .replace("з", "ź")

                .replace("Й", "J")
                .replace("й", "j")
                .replace("К", "K")
                .replace("к", "k")
                .replace("Л", "L")
                .replace("л", "l")
                .replace("М", "M")
                .replace("м", "m")
                .replace("Н", "N")
                .replace("н", "n")


                .replace("П", "P")
                .replace("п", "p")
                .replace("Р", "R")
                .replace("р", "r")
                .replace("С", "S")
                .replace("с", "s")
                .replace("Т", "T")
                .replace("т", "t")
                .replace("У", "U")
                .replace("у", "u")
                .replace("Ў", "Ŭ")
                .replace("ў", "ŭ")
                .replace("Ф", "F")
                .replace("ф", "f")
                .replace("Х", "Ch")
                .replace("х", "ch")
                .replace("Ц", "C")
                .replace("ц", "c")
                .replace("Ч", "Č")
                .replace("ч", "č")

                .replace("Ы", "Y")
                .replace("ы", "y")
                .replace("Ь", "")
                .replace("ь", "")
                .replace("Э", "E")
                .replace("э", "e")
                .replace("Ю", "Ju")
                .replace("ю", "ju")
                .replace("Я", "Ja")
                .replace("я", "ja")

                .replace("", "")


                ;

    }

}
