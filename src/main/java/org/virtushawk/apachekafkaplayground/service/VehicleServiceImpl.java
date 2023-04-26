package org.virtushawk.apachekafkaplayground.service;

import org.springframework.stereotype.Service;
import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;
import org.virtushawk.apachekafkaplayground.entity.VehicleSignalDTO;
import org.virtushawk.apachekafkaplayground.entity.exception.ValidationException;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    protected static final Map<String, VehiclePositionDTO> VEHICLE_MAP = new HashMap<>();

    @Override
    public void register(VehicleCreateDTO vehicleCreateDTO) {
        if (VEHICLE_MAP.containsKey(vehicleCreateDTO.getVehicleId())) {
            throw new ValidationException("Vehicle already registered");
        }

        VEHICLE_MAP.put(vehicleCreateDTO.getVehicleId(), null);
    }

    @Override
    public void updatePosition(VehicleSignalDTO signalDTO) {
        if (!VEHICLE_MAP.containsKey(signalDTO.getVehicleId())) {
            throw new ValidationException("Vehicle is not registered");
        }

        VEHICLE_MAP.put(signalDTO.getVehicleId(), signalDTO.getPosition());
    }
}
