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
    private static final int MAX_DEPTH = 3;
    private Set<String> visitedLinks = new HashSet<>();

    public Set<String> startCrawling(List<String> urls) {
        visitedLinks.clear();
        for (String url : urls) {
            crawl(1, url);
        }
        return visitedLinks;
    }

    private void crawl(int level, String url) {
        if (level <= MAX_DEPTH) {
            Document doc = request(url);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextLink = link.absUrl("href");
                    if (!visitedLinks.contains(nextLink)) {
                        visitedLinks.add(nextLink);
                        crawl(level + 1, nextLink);
                    }
                }
            }
        }
    }

    private Document request(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                System.out.println("Received web page at " + url);
                String title = doc.title();
                System.out.println("Title: " + title);
                visitedLinks.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
