package pe.fwani;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * Created by fwani.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
public class MyConsumer {
    private static final String TOPIC_NAME = "fwani";
    private static final String FIN_MESSAGE = "exit";
    private static final String BOOTSTRAP_SERVERS = "0.0.0.0:9092,0.0.0.0:9093";

    public static void main(String[] args) {
        var properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        try (var consumer = new KafkaConsumer<String, String>(properties)) {
            consumer.subscribe(List.of(TOPIC_NAME));
            String message = null;
            do {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));

                for (var record : records) {
                    message = record.value();
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), message);
                }
            } while (!(Objects.equals(message, FIN_MESSAGE)));
        }
    }
}
