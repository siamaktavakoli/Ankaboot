//package finalproject;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class RealTimeDataUrlExtractorService {
//
//    @Value("${app.topic.newApiTopic}")
//    private String kafkaTopicUrls; // Kafka topic where the URLs will be published
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public RealTimeDataUrlExtractorService(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public Set<String> extractAndPublishRealTimeDataUrls(String searchQuery) {
//        Set<String> realTimeDataUrls = new HashSet<>(); // Create a set to store the URLs
//
//        try {
//            // Construct the search URL dynamically based on the search query
//            String searchUrl = "https://www.google.com/search?q=" + searchQuery;
//
//            Document doc = Jsoup.connect(searchUrl).get();
//            Elements links = doc.select("a[href]");
//
//            for (var link : links) {
//                String href = link.attr("abs:href");
//                if (isRealTimeDataUrl(href)) {
//                    realTimeDataUrls.add(href); // Add the URL to the set
//                    kafkaTemplate.send(kafkaTopicUrls, href);
//                }
//            }
//        } catch (Exception e) {
//            // Handle exceptions (e.g., network errors or parsing errors)
//            // You can use the Error Handling Service class for this purpose
//        }
//        return realTimeDataUrls;
//    }
//
//    private boolean isRealTimeDataUrl(String url) {
//        // Check if the URL contains "stream" or "API" (case-insensitive)
//        return url.toLowerCase().contains("stream") || url.toLowerCase().contains("api");
//    }
//}
////
////package finalproject;
////
////import org.jsoup.Jsoup;
////import org.jsoup.nodes.Document;
////import org.jsoup.select.Elements;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.kafka.core.KafkaTemplate;
////import org.springframework.stereotype.Service;
////
////import java.util.HashSet;
////import java.util.Set;
////import java.util.concurrent.ExecutorService;
////import java.util.concurrent.Executors;
////
////@Service
////public class RealTimeDataUrlExtractorService {
////
////    @Value("${app.topic.newApiTopic}")
////    private String kafkaTopicUrls; // Kafka topic where the URLs will be published
////
////    private final KafkaTemplate<String, String> kafkaTemplate;
////
////    public RealTimeDataUrlExtractorService(KafkaTemplate<String, String> kafkaTemplate) {
////        this.kafkaTemplate = kafkaTemplate;
////    }
////
////    public Set<String> extractAndPublishRealTimeDataUrls(String searchQuery) {
////        Set<String> realTimeDataUrls = new HashSet<>(); // Create a set to store the URLs
////
////        try {
////            // Construct the search URL dynamically based on the search query
////            String searchUrl = "https://www.google.com/search?q=" + searchQuery;
////
////            Document doc = Jsoup.connect(searchUrl).get();
////            Elements links = doc.select("a[href]");
////
////            // Create a thread pool to execute the extraction and publishing concurrently
////            int numThreads = 5; // You can adjust the number of threads as needed
////            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
////
////            for (var link : links) {
////                String href = link.attr("abs:href");
////                if (isRealTimeDataUrl(href)) {
////                    realTimeDataUrls.add(href); // Add the URL to the set
////                    String urlToPublish = href;
////                    executorService.execute(() -> {
////                        kafkaTemplate.send(kafkaTopicUrls, urlToPublish);
////                    });
////                }
////            }
////
////            // Shutdown the thread pool when all tasks are completed
////            executorService.shutdown();
////        } catch (Exception e) {
////            // Handle exceptions (e.g., network errors or parsing errors)
////            // You can use the Error Handling Service class for this purpose
////        }
////        return realTimeDataUrls;
////    }
////
////    private boolean isRealTimeDataUrl(String url) {
////        // Check if the URL contains "stream" or "API" (case-insensitive)
////        return url.toLowerCase().contains("stream") || url.toLowerCase().contains("api");
////    }
////}
//
