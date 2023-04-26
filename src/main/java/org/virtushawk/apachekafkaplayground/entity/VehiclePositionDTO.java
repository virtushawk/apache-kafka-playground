package org.virtushawk.apachekafkaplayground.entity;

public class VehiclePositionDTO extends BaseDTO {

    private long x;

    private long y;

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
