
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("app/src/main/resources/recentquotes.json");
        String URL = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";

            try {
                HttpURLConnection   connection = createConnection(URL);
                QuoteFromAPI newQuote = printRandomFromAPI(connection);
                List<RecentQuotes> listQuotes = addQuoteAndGetRecentQuotes(path, newQuote);
                String outputJson = convertToJSON(listQuotes);
                writeAllQuotesToTheFile(path, outputJson);
            } catch (IOException e) {
                printRandomQuoteFromTheFile(path);
            }
        }


    private static HttpURLConnection createConnection(String URL) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

        static QuoteFromAPI printRandomFromAPI(HttpURLConnection connection) throws IOException {
        BufferedReader reader = readJsonFromTheAPI(connection);
        String quote = reader.readLine();
        Gson gson = new Gson();
        QuoteFromAPI newQuote = gson.fromJson(quote, QuoteFromAPI.class);
        System.out.println(newQuote);
        connection.disconnect();
        reader.close();
        return newQuote;
    }


    private static BufferedReader readJsonFromTheAPI(HttpURLConnection conn) throws IOException {
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader reader = new BufferedReader(in);
        return reader;
    }

    private static List<RecentQuotes> addQuoteAndGetRecentQuotes(Path path, QuoteFromAPI newQuote) {
        RecentQuotes[] allQuotes = convertFileFromJsonToArrayOfRecentQuotes(path);
        List<RecentQuotes> listOfQuotes = new ArrayList<>(Arrays.asList(allQuotes));
        RecentQuotes quote = new RecentQuotes();
        quote.setAuthor(newQuote.quoteAuthor) ;
        quote.setText(newQuote.quoteText);
        quote.setLikes(null);
        quote.setTags(null);
        listOfQuotes.add(quote);
        return listOfQuotes;
    }

    private static RecentQuotes[] convertFileFromJsonToArrayOfRecentQuotes(Path path) {
        Gson gson = new Gson();
        Reader reader = readTheFile(path);
        RecentQuotes[] recentQuotes = gson.fromJson(reader, RecentQuotes[].class);
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recentQuotes;
    }
    private static Reader readTheFile(Path path) {
        try {
            Reader reader = new FileReader(path.toString());
            return reader;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private static String convertToJSON(List<RecentQuotes> listQuotes) {
        Gson gson = new Gson();
        gson = gson.newBuilder().setPrettyPrinting().create();
        String output = gson.toJson(listQuotes);
        return output;
    }


    private static void writeAllQuotesToTheFile(Path path, String outputJson) throws IOException {
        FileWriter fw = new FileWriter(path.toString());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(outputJson);
        bw.flush();
        bw.close();
    }


    private static void printRandomQuoteFromTheFile(Path path) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(String.valueOf(path))) {
            RecentQuotes[] recentQuotes = gson.fromJson(reader, RecentQuotes[].class);
            Random r = new Random();
            int index = r.nextInt(recentQuotes.length);
            System.out.println(recentQuotes[index]);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

