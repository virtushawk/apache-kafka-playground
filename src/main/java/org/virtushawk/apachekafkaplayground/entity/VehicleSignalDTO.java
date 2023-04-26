package org.virtushawk.apachekafkaplayground.entity;

import jakarta.validation.constraints.NotNull;

public class VehicleSignalDTO extends BaseDTO {

    @NotNull
    String vehicleId;

    @NotNull
    VehiclePositionDTO position;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehiclePositionDTO getPosition() {
        return position;
    }

    public void setPosition(VehiclePositionDTO position) {
        this.position = position;
    }
}
