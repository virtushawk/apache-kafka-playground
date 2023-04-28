package org.virtushawk.apachekafkaplayground.entity;

/**
 * Base DTO contains common fields for all DTOs
 */
public abstract class BaseDTO {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
