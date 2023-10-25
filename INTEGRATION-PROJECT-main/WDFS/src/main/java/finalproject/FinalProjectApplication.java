package finalproject;
//String url = "https://mixedanalytics.com/blog/list-actually-free-open-no-auth-needed-apis/"; // URL to the webpage to scrape

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class FinalProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

}



