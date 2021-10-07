package quotes;


public class QuoteFromAPI {
    String quoteText;
    String quoteAuthor;

    @Override
    public String toString() {
        return "Today's Quote From API: \n" +
                "quote: " + quoteText + "\n" +
                "Author: " + quoteAuthor ;
    }
}