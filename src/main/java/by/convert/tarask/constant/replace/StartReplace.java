package by.convert.tarask.constant.replace;

import by.util.Pair;

import java.util.ArrayList;

public class StartReplace {
    private static StartReplace single_instance = null;


    private ArrayList<Pair> startReplace;


    private StartReplace() {
        this.startReplace = new ArrayList<>();
        startReplace.add(new Pair("бельгі", "бэльгі"));
        startReplace.add(new Pair("ірланд", "ірлянд"));
        startReplace.add(new Pair("люксембург", "люксэмбург"));
        startReplace.add(new Pair("нідэрланд", "нідэрлянд"));
        startReplace.add(new Pair("швейцар", "швайцар"));
        startReplace.add(new Pair("швецы", "швэцы"));

        startReplace.add(new Pair("інструмент", "інструмэнт"));
    }

    public static StartReplace getInstance() {
        if (single_instance == null)
            single_instance = new StartReplace();

        return single_instance;
    }

    public static ArrayList<Pair> getStartReplaces() {
        return getInstance().startReplace;
    }
}
