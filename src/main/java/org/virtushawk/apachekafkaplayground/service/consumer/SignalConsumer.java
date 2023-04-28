package org.virtushawk.apachekafkaplayground.service.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.virtushawk.apachekafkaplayground.ApacheKafkaPlaygroundApplication;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionUpdateDTO;
import org.virtushawk.apachekafkaplayground.service.VehicleService;
import org.virtushawk.apachekafkaplayground.service.producer.SignalProducer;

/**
 * Consumer for signal messages
 */
@Service
@KafkaListener(topics = SignalProducer.SIGNAL_TOPIC,
        groupId = ApacheKafkaPlaygroundApplication.SIGNAL_CONSUMER_GROUP, concurrency = "3")
public class SignalConsumer {

    private static final Logger LOGGER = LogManager.getLogger(SignalConsumer.class);

    private final VehicleService vehicleService;

    public SignalConsumer(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Consume message and calculate position. Sends new position to output topic(logging)
     *
     * @param key messageKey
     * @param positionDTO position dto
     * @return message with new position and vehicleKey as messageKey
     */
    @KafkaHandler
    @SendTo(value = ApacheKafkaPlaygroundApplication.POSITION_TOPIC)
    public Message<VehiclePositionDTO> consume(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                           @Payload VehiclePositionUpdateDTO positionDTO) {
        LOGGER.info("Received Signal from : {} with position {}", key, positionDTO);

        return MessageBuilder
                .withPayload(vehicleService.calculatePosition(key, positionDTO))
                .setHeader(KafkaHeaders.KEY, key)
                .build();
    }
}
