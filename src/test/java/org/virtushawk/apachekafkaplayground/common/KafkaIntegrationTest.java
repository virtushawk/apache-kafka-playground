package org.virtushawk.apachekafkaplayground.common;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.core.BrokerAddress;

@EmbeddedKafka(value = 2, ports = {9092, 9093})
public abstract class KafkaIntegrationTest extends BaseWithContextTest {

    @Value("${spring.kafka.topic.name}")
    protected String defaultTopicName;

    @Autowired
    protected EmbeddedKafkaBroker embeddedKafkaBroker;

    @Before
    public void before() {
        embeddedKafkaBroker.addTopics(
               new NewTopic(defaultTopicName, 3, (short) 2)
        );
    }

    protected BrokerAddress getBrokerAddress() {
       return embeddedKafkaBroker.getBrokerAddress(0);
    }
}
