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
    private final WebCrawlerService webCrawlerService;
    @Autowired
    private Sender sender;

    @GetMapping("/crawl")
    public void startCrawling(@RequestParam String searchQuery) {
        // Here, you can use the list of URLs from your original code
        List<String> urlsToCrawl = new ArrayList<>();
        urlsToCrawl.add("https://www.abcnews.com");
        urlsToCrawl.add("https://www.npr.org");
        urlsToCrawl.add("https://www.nytimes.com");

        Set<String> crawledLinks = webCrawlerService.startCrawling(urlsToCrawl);
        for (String cr: crawledLinks) {
            System.out.println(cr);
        }


        sender.send("Message");


        crawledLinks = filterLinks(crawledLinks, searchQuery);
        for (String urls:crawledLinks
               ) {
            System.out.println(urls);

        }

         crawledLinks.forEach(System.out::println);
    }


    private Set<String> filterLinks(Set<String> links, String searchQuery) {

        Set<String> filteredLinks = new HashSet<>();
        for (String link : links) {
            if (link.contains(searchQuery)) {
                filteredLinks.add(link);
            }
        }
        return filteredLinks;
    }
}

