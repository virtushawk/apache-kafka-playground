package org.virtushawk.apachekafkaplayground.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.virtushawk.apachekafkaplayground.common.KafkaIntegrationTest;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

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
       properties.put("bootstrap.servers", "localhost:9092");
       properties.put("linger.ms", "1");
       properties.put("key.serializer", StringSerializer.class);
       properties.put("value.serializer", StringSerializer.class);

       //At least once semantic
       properties.put("acks", "1");

       return properties;
   }

   private Properties getConsumerProperties() {
       Properties properties = new Properties();
       properties.put("bootstrap.servers", "localhost:9092");
       properties.put("group.id", "test");
       properties.put("key.deserializer", StringDeserializer.class);
       properties.put("value.deserializer", StringDeserializer.class);

       //At most once semantic
       properties.put("acks", "0");
       return properties;
   }
}
