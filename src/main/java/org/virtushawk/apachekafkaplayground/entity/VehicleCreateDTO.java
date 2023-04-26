package org.virtushawk.apachekafkaplayground.entity;

import jakarta.validation.constraints.NotNull;

public class VehicleCreateDTO extends BaseDTO {

    @NotNull
    private String vehicleId;

    private String name;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}