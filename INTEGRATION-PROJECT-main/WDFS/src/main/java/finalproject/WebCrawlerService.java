package finalproject;

import lombok.Data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Data
public class WebCrawlerService {
    // This service class provides web crawling functionality to discover and collect links from web pages.

    private static final int MAX_DEPTH = 3;
    // Maximum depth of crawling, limiting how far the crawler will traverse links.

    private Set<String> visitedLinks = new HashSet<>();
    // A set to keep track of visited links, avoiding duplicate requests.

    public Set<String> startCrawling(List<String> urls) {
        // Initiates the web crawling process and returns a set of visited links.
        visitedLinks.clear(); // Clear the visited links set before starting.

        for (String url : urls) {
            crawl(1, url); // Start crawling from the provided URLs.
        }
        return visitedLinks; // Return the set of visited links.
    }

    private void crawl(int level, String url) {
        // Recursive method to crawl web pages to the specified depth.
        if (level <= MAX_DEPTH) {
            Document doc = request(url);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextLink = link.absUrl("href");
                    if (!visitedLinks.contains(nextLink)) {
                        visitedLinks.add(nextLink);
                        crawl(level + 1, nextLink); // Recursively crawl the next link.
                    }
                }
            }
        }
    }

    private Document request(String url) {
        // Sends an HTTP request to the specified URL and retrieves the web page content.
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                System.out.println("Received web page at " + url);
                String title = doc.title();
                System.out.println("Title: " + title);
                visitedLinks.add(url); // Mark the URL as visited.
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null; // Handle IO exceptions and return null for unsuccessful requests.
        }
    }
}
