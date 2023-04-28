package org.virtushawk.apachekafkaplayground.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.entity.VehiclePositionUpdateDTO;
import org.virtushawk.apachekafkaplayground.service.VehicleService;

/**
 * API for vehicle requests
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleResource {

    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Register new vehicle
     *
     * @param createDTO createDTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerVehicle(@Valid @RequestBody VehicleCreateDTO createDTO) {
        vehicleService.register(createDTO);
    }

    /**
     * Update vehicle position
     *
     * @param id ID of vehicle
     * @param positionDTO positionDTO containing new position
     */
    @PutMapping("{id}/signal")
    @ResponseStatus(HttpStatus.OK)
    public void updatePosition( @PathVariable String id, @Valid @RequestBody VehiclePositionUpdateDTO positionDTO) {
        vehicleService.updatePosition(id, positionDTO);
    }
}
