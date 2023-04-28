package org.virtushawk.apachekafkaplayground.service;

import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionUpdateDTO;
import org.virtushawk.apachekafkaplayground.entity.exception.ValidationException;
import org.virtushawk.apachekafkaplayground.service.consumer.SignalConsumer;

/**
 * Service for Vehicle
 */
public interface VehicleService {

    /**
     * Add vehicle to cache for registered vehicles. Allows you to send position signals
     *
     * @param vehicleCreateDTO vehicle create dto
     * @throws ValidationException if vehicle already exists
     */
    void register(VehicleCreateDTO vehicleCreateDTO);

    /**
     * Update position of the vehicle. Sends position message to {@link SignalConsumer}
     *
     * @param vehicleId vehicle id
     * @param positionDTO position dto
     * @throws ValidationException if vehicle doesn't exist
     */
    void updatePosition(String vehicleId, VehiclePositionUpdateDTO positionDTO);

    /**
     * Calculate new information about position
     *
     * @param vehicleId vehicle id
     * @param newPosition new position
     * @throws ValidationException if vehicle doesn't exist or vehicle ID is empty
     * @return updated vehicle position
     */
    VehiclePositionDTO calculatePosition(String vehicleId, VehiclePositionUpdateDTO newPosition);
}
