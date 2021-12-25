package by.excel.parser.glossary;

public class Record {


    int id;
    private String originalValue;
    private TranslateInfo tarask;
    private TranslateInfo narkam;
    private TranslateInfo lacink;

    public Record() {
    }

    public Record(int id, String originalValue,
                  String taraskValue, String taraskWrong, String taraskComment,
                  String acadValue, String acadWrong, String acadComment,
                  String lacinkaValue, String lacinkaWrong, String lacinkaComment) {
        this.id = id;
        this.originalValue = originalValue;
        this.tarask = new TranslateInfo(taraskValue, taraskWrong, taraskComment);
        this.narkam = new TranslateInfo(acadValue, acadWrong, acadComment);
        this.lacink = new TranslateInfo(lacinkaValue, lacinkaWrong, lacinkaComment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public TranslateInfo getTarask() {
        return tarask;
    }

    public void setTarask(TranslateInfo tarask) {
        this.tarask = tarask;
    }

    public TranslateInfo getNarkam() {
        return narkam;
    }

    public void setNarkam(TranslateInfo narkam) {
        this.narkam = narkam;
    }

    public TranslateInfo getLacink() {
        return lacink;
    }

    public void setLacink(TranslateInfo lacink) {
        this.lacink = lacink;
    }


}