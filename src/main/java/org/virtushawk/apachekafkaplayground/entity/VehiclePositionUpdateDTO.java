package org.virtushawk.apachekafkaplayground.entity;

import jakarta.validation.constraints.NotNull;

public class VehiclePositionUpdateDTO extends BaseDTO {

    @NotNull(message = "X coordinate should not be null")
    private Double x;

    @NotNull(message = "Y coordinate should not be null")
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "VehiclePositionDTO{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
