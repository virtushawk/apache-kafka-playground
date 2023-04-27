package org.virtushawk.apachekafkaplayground.service;

import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionUpdateDTO;

public interface VehicleService {

    void register(VehicleCreateDTO vehicleCreateDTO);

    void updatePosition(String vehicleId, VehiclePositionUpdateDTO positionDTO);

    VehiclePositionDTO calculatePosition(String vehicleId, VehiclePositionUpdateDTO newPosition);
}
