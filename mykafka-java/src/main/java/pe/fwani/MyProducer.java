package pe.fwani;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * <p>
 * Created by fwani.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
public class MyProducer {
    private static final String TOPIC_NAME = "fwani";
    private static final String FIN_MESSAGE = "exit";
    private static final String BOOTSTRAP_SERVERS = "0.0.0.0:9092,0.0.0.0:9093";

    public static void main(String[] args) {
        var properties = new Properties();
        // broker 정보 정의
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        // Record 의 key 와 value 를 bytes 로 serialize 하기 위한 것
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.ACKS_CONFIG, "1");

        // kafka producer 생성
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // stdin 에서 인풋 데이터를 받기 위한 정의
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Input > ");
            // line 읽기
            var message = sc.nextLine();

            // stdin 에서 읽은 값을 value 로 세팅
            var record = new ProducerRecord<String, String>(TOPIC_NAME, message);
            try {
                // message 보내기
                producer.send(record, ((metadata, exception) -> {
                    if (exception != null) {
                        System.out.println("Send failed reason: " + exception.getMessage());
                    }
                }));
            } finally {
                producer.flush();
            }

            if (message.equals(FIN_MESSAGE)) {
                producer.close();
                break;
            }
        }
    }

}