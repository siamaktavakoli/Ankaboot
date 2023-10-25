package finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    // This class is responsible for sending messages to a Kafka topic.

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    // Autowired KafkaTemplate for sending Kafka messages.

    @Value("${app.topic.newApiTopic}")
    private String topic;
    // Load the Kafka topic name from application properties.

    public void send(String message) {
        // Sends the specified message to the configured Kafka topic.

        System.out.println("sending message=" + message + " to topic=" + topic);
        // Print a log message indicating the message being sent and the target topic.

        kafkaTemplate.send(topic, message);
        // Use KafkaTemplate to send the message to the Kafka topic.
    }
}
