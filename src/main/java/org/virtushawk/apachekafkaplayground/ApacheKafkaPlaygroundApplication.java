package org.virtushawk.apachekafkaplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableKafka
@EnableTransactionManagement
public class ApacheKafkaPlaygroundApplication {

    public static final String SIGNAL_CONSUMER_GROUP = "signal-consumer-group";

    public static final String POSITION_TOPIC = "position-topic";

    public static void main(String[] args) {
        SpringApplication.run(ApacheKafkaPlaygroundApplication.class, args);
    }
}
