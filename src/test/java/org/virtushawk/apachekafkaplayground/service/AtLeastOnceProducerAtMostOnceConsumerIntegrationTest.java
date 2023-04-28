package org.virtushawk.apachekafkaplayground.service;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.virtushawk.apachekafkaplayground.common.KafkaIntegrationTest;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * At least once producer at most once consumer integration test
 */
class AtLeastOnceProducerAtMostOnceConsumerIntegrationTest extends KafkaIntegrationTest {

   @SuppressWarnings("JUnitMixedFramework")
   @Test
   void shouldImplementModel() {
       String messagePayload = "Hello!";

       sendMessage(messagePayload);

       ConsumerRecords<String, String> messages = receiveMessage();

       Assertions.assertEquals(1, messages.count());

       for (ConsumerRecord<String, String> record: messages.records(defaultTopicName)) {
           Assertions.assertEquals(messagePayload, record.value());
       }
   }

   private void sendMessage(String message) {
       try (KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProperties())) {
           producer.send(new ProducerRecord<>(defaultTopicName, message));
       }
   }

   private ConsumerRecords<String, String> receiveMessage() {
       ConsumerRecords<String, String> consumerRecords;
       try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties())) {
           consumer.subscribe(List.of(defaultTopicName));

           do {
               consumerRecords = consumer.poll(Duration.ofMillis(1000));

           } while (consumerRecords.isEmpty());

           return consumerRecords;
       }
   }

   private Properties getProducerProperties() {
       Properties properties = new Properties();
       properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
       properties.put(ProducerConfig.LINGER_MS_CONFIG, "1");
       properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

       //At least once producer semantic
       properties.put(ProducerConfig.ACKS_CONFIG, "1");

       return properties;
   }

   private Properties getConsumerProperties() {
       Properties properties = new Properties();
       properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
       properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
       properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
       properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
       properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

       //At most once consumer semantic
       properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
       properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
       return properties;
   }
}
