package quotes;

import java.util.ArrayList;
import java.util.List;

public class RecentQuotes {
    private String author;
    private String text;
    List<String> tags = new ArrayList<>();
    String likes;


    public RecentQuotes(String author, String text) {
        this.author = author;
        this.text = text;

    }

    public RecentQuotes() {

    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "RecentQuotes{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
