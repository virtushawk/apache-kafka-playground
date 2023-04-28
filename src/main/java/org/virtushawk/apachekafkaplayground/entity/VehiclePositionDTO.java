package org.virtushawk.apachekafkaplayground.entity;

/**
 * DTO to store vehicle information about position
 */
public class VehiclePositionDTO extends BaseDTO {

    private Double x;

    private Double y;

    private Double totalDistance;

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

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "VehiclePositionDTO{" +
                "x=" + x +
                ", y=" + y +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
