package by.parser;

public class ParsedElement {

    private String delimiter;
    private String originalWord;
    private String word;
    private UpperCase upperCase;

    public ParsedElement(String delimiter, String originalWord, UpperCase upperCase) {
        this.delimiter = delimiter;
        this.originalWord = originalWord;
        this.word = originalWord.toLowerCase();
        this.upperCase = upperCase;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getWord() {
        return word;
    }

    public UpperCase getUpperCase() {
        return upperCase;
    }

    @Override
    public String toString() {
        return "ParsedElement{" +
                "delimiter='" + delimiter + '\'' +
                ", originalWord='" + originalWord + '\'' +
                ", word='" + word + '\'' +
                ", upperCase=" + upperCase +
                '}';
    }
}
