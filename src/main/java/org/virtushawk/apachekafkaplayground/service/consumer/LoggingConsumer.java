package org.virtushawk.apachekafkaplayground.service.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.virtushawk.apachekafkaplayground.ApacheKafkaPlaygroundApplication;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;

@Service
@KafkaListener(id = "logging-consumer", topics = ApacheKafkaPlaygroundApplication.POSITION_TOPIC)
public class LoggingConsumer {

    private static final Logger LOGGER = LogManager.getLogger(LoggingConsumer.class);

    @KafkaHandler
    public void consume(@Header(KafkaHeaders.RECEIVED_KEY) String key,
                                      @Payload VehiclePositionDTO positionDTO) {
        LOGGER.info("Vehicle {} updated position {}, total distance travelled : {}",
                key, positionDTO, positionDTO.getTotalDistance());
    }
}
