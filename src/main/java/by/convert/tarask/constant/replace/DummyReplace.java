package by.convert.tarask.constant.replace;

import by.util.Pair;

import java.util.ArrayList;

public class DummyReplace {
    private static DummyReplace single_instance = null;


    private ArrayList<Pair> dummyReplace;


    private DummyReplace() {
        this.dummyReplace = new ArrayList<>();
        dummyReplace.add(new Pair("амерык", "амэрык"));
        dummyReplace.add(new Pair("анверт", "анвэрт"));
        dummyReplace.add(new Pair("аргумент", "аргумэнт"));
        dummyReplace.add(new Pair("артапед", "артапэд"));
        dummyReplace.add(new Pair("арфаграф", "артаграф"));
        dummyReplace.add(new Pair("арыстоцель", "арыстотэль"));
        dummyReplace.add(new Pair("валанцёр", "валантэр"));
        dummyReplace.add(new Pair("версі", "вэрсі"));
        dummyReplace.add(new Pair("вулкан", "вулькан"));
        dummyReplace.add(new Pair("гаус", "гаўс"));
        dummyReplace.add(new Pair("Генры", "Гэнры"));
        dummyReplace.add(new Pair("гласар", "глясар"));
        dummyReplace.add(new Pair("дакумент", "дакумэнт"));
        dummyReplace.add(new Pair("донья", "доньня"));
        dummyReplace.add(new Pair("еўр", "эўр"));
        dummyReplace.add(new Pair("іерогліф", "ерогліф"));
        dummyReplace.add(new Pair("каментарый", "камэнтар"));
        dummyReplace.add(new Pair("каталог", "каталёг"));
        dummyReplace.add(new Pair("клас", "кляс"));
        dummyReplace.add(new Pair("клуб", "клюб"));
        dummyReplace.add(new Pair("лагіч", "лягіч"));
        dummyReplace.add(new Pair("лампада", "лямпада"));
        dummyReplace.add(new Pair("логік", "лёгік"));
        dummyReplace.add(new Pair("логія", "лёгія"));
        dummyReplace.add(new Pair("мекка", "мэка"));
        dummyReplace.add(new Pair("мільянер", "мільянэр"));
        dummyReplace.add(new Pair("музей", "музэй"));
        dummyReplace.add(new Pair("мушкіцёр", "мушкітэр"));
        dummyReplace.add(new Pair("пазіцы", "пазыцы"));
        dummyReplace.add(new Pair("рыдыус", "радыюс"));
        dummyReplace.add(new Pair("рэклам", "рэклям"));
        dummyReplace.add(new Pair("саліцёр", "слітэр"));
        dummyReplace.add(new Pair("сігнал", "сыгнал"));
        dummyReplace.add(new Pair("сістэм", "сыстэм"));
        dummyReplace.add(new Pair("фунікулёр", "фунікулер"));
        dummyReplace.add(new Pair("фальклор", "фальклёр"));
        dummyReplace.add(new Pair("шоу", "шоў"));
    }

    public static DummyReplace getInstance() {
        if (single_instance == null)
            single_instance = new DummyReplace();

        return single_instance;
    }

    public static ArrayList<Pair> getDummyReplaces() {
        return getInstance().dummyReplace;
    }
}
