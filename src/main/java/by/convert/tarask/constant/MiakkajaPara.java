package by.convert.tarask.constant;

import java.util.ArrayList;

public class MiakkajaPara {


    private static MiakkajaPara single_instance = null;


    private ArrayList<String> miakkijaPary;


    private MiakkajaPara() {
        miakkijaPary = new ArrayList<>();
        for(int i = 0; i<ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().size(); i++){
            for (int j=0; j<Zmiakchatel.getZmiakcaceli().size(); j++){
                miakkijaPary.add(ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().get(i)+Zmiakchatel.getZmiakcaceli().get(j));
            }
        }
    }

    public static MiakkajaPara getInstance() {
        if (single_instance == null)
            single_instance = new MiakkajaPara();

        return single_instance;
    }

    public static ArrayList<String> getMiakkijaPary() {
        return getInstance().miakkijaPary;
    }
}
