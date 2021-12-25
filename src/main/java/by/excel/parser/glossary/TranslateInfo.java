package by.excel.parser.glossary;

public class TranslateInfo {

    private String value;
    private String wrong;
    private String comment;

    public TranslateInfo(String value, String wrong, String comment) {
        this.value = value;
        this.wrong = wrong;
        this.comment = comment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
