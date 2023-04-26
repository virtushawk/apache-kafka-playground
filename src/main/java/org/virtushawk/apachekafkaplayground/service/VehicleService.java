package org.virtushawk.apachekafkaplayground.service;

import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.entity.VehicleSignalDTO;

public interface VehicleService {

    void register(VehicleCreateDTO vehicleCreateDTO);

    void updatePosition(VehicleSignalDTO signalDTO);
}
