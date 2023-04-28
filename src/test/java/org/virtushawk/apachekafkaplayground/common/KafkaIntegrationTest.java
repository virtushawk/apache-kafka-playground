package org.virtushawk.apachekafkaplayground.common;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.core.BrokerAddress;

/**
 * Base test with embedded kafka. Uses two node setup with ports 9092, 9093.
 * <p>
 *     Before each method it will try to create default test-topic with 3 partitions and replication factor 2
 * </p>
 */
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
