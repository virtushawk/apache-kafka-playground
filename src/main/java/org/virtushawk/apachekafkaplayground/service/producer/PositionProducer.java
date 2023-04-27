package org.virtushawk.apachekafkaplayground.service.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;

@Service
public class PositionProducer {

    public static final Logger LOGGER = LogManager.getLogger(PositionProducer.class);

    public static final String POSITION_TOPIC = "position-topic";

    private final KafkaTemplate<String, VehiclePositionDTO> kafkaTemplate;

    public PositionProducer(KafkaTemplate<String, VehiclePositionDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPosition(String vehicleId, VehiclePositionDTO positionDTO) {
        kafkaTemplate.send(POSITION_TOPIC, vehicleId, positionDTO);
        LOGGER.info("Sent position to : {}", POSITION_TOPIC);
    }
}
