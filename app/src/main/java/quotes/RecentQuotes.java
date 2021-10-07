package quotes;

import java.util.ArrayList;
import java.util.List;

public class RecentQuotes {
    private String author;
    private String text;
    private String likes;
    private String tags[];


    public RecentQuotes(String author, String text, String likes, String[] tags) {
        this.author = author;
        this.text = text;
        this.likes = likes;
        this.tags = tags;
    }

    public RecentQuotes() {

    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
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
