package finalproject;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/crawler")
@RequiredArgsConstructor
public class WebScraperController {
    // This controller handles web scraping and link filtering operations.

    private final WebCrawlerService webCrawlerService;
    // Autowired instance of WebCrawlerService to initiate web crawling.

    @Autowired
    private Sender sender;
    // Autowired instance of Sender to send messages to a Kafka topic.

    @GetMapping("/crawl")
    public void startCrawling(@RequestParam String searchQuery) {
        // Initiates the web crawling process and link filtering based on a search query.
        // You can use the list of URLs from your original code or modify it as needed.
        List<String> urlsToCrawl = new ArrayList<>();
        urlsToCrawl.add("https://www.abcnews.com");
        urlsToCrawl.add("https://www.npr.org");
        urlsToCrawl.add("https://www.nytimes.com");

        Set<String> crawledLinks = webCrawlerService.startCrawling(urlsToCrawl);
        for (String cr : crawledLinks) {
            System.out.println(cr);
        }

        sender.send("Message");

        crawledLinks = filterLinks(crawledLinks, searchQuery);
        for (String urls : crawledLinks) {
            System.out.println(urls);
        }

        crawledLinks.forEach(System.out::println);
    }

    private Set<String> filterLinks(Set<String> links, String searchQuery) {
        // Filters links based on the provided search query.
        Set<String> filteredLinks = new HashSet<>();
        for (String link : links) {
            if (link.contains(searchQuery)) {
                filteredLinks.add(link);
            }
        }
        return filteredLinks;
    }
}
