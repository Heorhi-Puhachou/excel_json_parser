package by.convert.tarask.constant.replace;

import by.util.Pair;

import java.util.ArrayList;

public class EndReplace {
    private static EndReplace single_instance = null;


    private ArrayList<Pair> endReplace;


    private EndReplace() {
        this.endReplace = new ArrayList<>();
        endReplace.add(new Pair("метр", "метар"));
        endReplace.add(new Pair("літр", "літар"));
    }

    public static EndReplace getInstance() {
        if (single_instance == null)
            single_instance = new EndReplace();

        return single_instance;
    }

    public static ArrayList<Pair> getEndReplaces() {
        return getInstance().endReplace;
    }
}
