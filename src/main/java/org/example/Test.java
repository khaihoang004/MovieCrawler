package org.example;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    private static String currentContent = "";

    public static void main(String[] args) {
        // Make the initial request
        makeInitialRequest();

        // Schedule a task to check for updates periodically
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkForUpdates();
            }
        }, 0, 5000); // Check for updates every 5 seconds (adjust as needed)
    }

    private static void makeInitialRequest() {
        try {
            // Construct the URL for the initial request
            String initialUrl = "https://www.themoviedb.org/movie/967847";

            // Make the initial GET request
            Connection.Response response = Jsoup.connect(initialUrl).execute();
            Document document = response.parse();

            // Process the initial response
            processPage(document);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkForUpdates() {
        try {
            // Construct the URL for checking updates
            String updateUrl = "https://www.themoviedb.org/movie/693134-dune-part-two/remote/rating/details?translate=false&language=en-US&mobile=false";

            // Make the request to check for updates
            Connection.Response response = Jsoup.connect(updateUrl).ignoreContentType(true).execute();
            String updatedContent = response.body();

            // Process the updated content if it's different from the current content
            if (!updatedContent.equals(currentContent)) {
                System.out.println("New content detected. Processing...");
                processPage(Jsoup.parse(updatedContent));
                currentContent = updatedContent;
            } else {
                System.out.println("No new content.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processPage(Document document) {
        // Process the content for crawling
        // This method should contain your scraping logic using Jsoup
        // Example: Select the element using the provided selector
        Element ratingElement = document.selectFirst("#rating_details_window > div > div:nth-child(2) > div > div:nth-child(2) > h3");

        if (ratingElement != null) {
            // If the element is found, print its text
            System.out.println("Rating: " + ratingElement.text());
        } else {
            System.out.println("Rating element not found.");
        }
    }
}
