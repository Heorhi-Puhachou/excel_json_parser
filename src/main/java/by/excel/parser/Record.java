package by.excel.parser;

public class Record {
    private String originalValue;
    private TranslateInfo tarask;
    private TranslateInfo acad;
    private TranslateInfo lacinka;

    public Record() {
    }

    public Record(String originalValue,
                  String taraskValue, String taraskWrong, String taraskComment,
                  String acadValue, String acadWrong, String acadComment,
                  String lacinkaValue, String lacinkaWrong, String lacinkaComment) {
        this.originalValue = originalValue;
        this.tarask = new TranslateInfo(taraskValue, taraskWrong, taraskComment);
        this.acad = new TranslateInfo(acadValue, acadWrong, acadComment);
        this.lacinka = new TranslateInfo(lacinkaValue, lacinkaWrong, lacinkaComment);
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

    public TranslateInfo getAcad() {
        return acad;
    }

    public void setAcad(TranslateInfo acad) {
        this.acad = acad;
    }

    public TranslateInfo getLacinka() {
        return lacinka;
    }

    public void setLacinka(TranslateInfo lacinka) {
        this.lacinka = lacinka;
    }


}