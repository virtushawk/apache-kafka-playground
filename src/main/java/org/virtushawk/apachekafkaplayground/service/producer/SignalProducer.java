package org.virtushawk.apachekafkaplayground.service.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionUpdateDTO;

/**
 * Producer for signal messages to input topic
 */
@Service
public class SignalProducer {

    public static final Logger LOGGER = LogManager.getLogger(SignalProducer.class);

    public static final String SIGNAL_TOPIC = "signal-topic";

    private final KafkaTemplate<String, VehiclePositionUpdateDTO> kafkaTemplate;

    public SignalProducer(KafkaTemplate<String, VehiclePositionUpdateDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send signal to topic.
     *
     * <p>
     *  This method is transactional and will wrap poll(input) > calculate distance > publish(output)
     *  flow into Kafka transactions
     * </p>
     *
     * @param vehicleId vehicle id
     * @param positionDTO position
     */
    @Transactional
    public void sendSignal(String vehicleId, VehiclePositionUpdateDTO positionDTO) {
        kafkaTemplate.send(SIGNAL_TOPIC, vehicleId, positionDTO);
        LOGGER.info("Sent signal to : {}", SIGNAL_TOPIC);
    }
}
