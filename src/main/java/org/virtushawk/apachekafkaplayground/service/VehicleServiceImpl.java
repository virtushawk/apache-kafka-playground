package org.virtushawk.apachekafkaplayground.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionUpdateDTO;
import org.virtushawk.apachekafkaplayground.entity.exception.ValidationException;
import org.virtushawk.apachekafkaplayground.service.producer.SignalProducer;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link VehicleService}
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    protected static final Map<String, VehiclePositionDTO> VEHICLE_MAP = new HashMap<>();

    private final SignalProducer signalProducer;

    public VehicleServiceImpl(SignalProducer signalProducer) {
        this.signalProducer = signalProducer;
    }

    @Override
    public void register(VehicleCreateDTO vehicleCreateDTO) {
        if (VEHICLE_MAP.containsKey(vehicleCreateDTO.getVehicleId())) {
            throw new ValidationException("Vehicle already registered");
        }

        VehiclePositionDTO vehiclePositionDTO = new VehiclePositionDTO();
        vehiclePositionDTO.setX(0D);
        vehiclePositionDTO.setY(0D);
        vehiclePositionDTO.setTotalDistance(0D);

        VEHICLE_MAP.put(vehicleCreateDTO.getVehicleId(), vehiclePositionDTO);
    }

    @Override
    public void updatePosition(String vehicleId, VehiclePositionUpdateDTO positionDTO) {
        if (!VEHICLE_MAP.containsKey(vehicleId)) {
            throw new ValidationException("Vehicle is not registered");
        }

        signalProducer.sendSignal(vehicleId, positionDTO);
    }

    @Override
    public VehiclePositionDTO calculatePosition(String vehicleId, VehiclePositionUpdateDTO newPosition) {
        if (StringUtils.isBlank(vehicleId) || !VEHICLE_MAP.containsKey(vehicleId)) {
            throw new ValidationException("Vehicle is not registered");
        }

        VehiclePositionDTO vehiclePosition = VEHICLE_MAP.get(vehicleId);

        double distance = calculateDistance(vehiclePosition.getX(), vehiclePosition.getY(),
                newPosition.getX(), newPosition.getY());

        vehiclePosition.setX(newPosition.getX());
        vehiclePosition.setY(newPosition.getY());
        vehiclePosition.setTotalDistance(vehiclePosition.getTotalDistance() + distance);

        return vehiclePosition;
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
       return Point2D.distance(x1, y1, x2, y2);
    }

}
