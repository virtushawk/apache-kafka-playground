package org.virtushawk.apachekafkaplayground.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.virtushawk.apachekafkaplayground.entity.VehicleCreateDTO;
import org.virtushawk.apachekafkaplayground.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleResource {

    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerVehicle(@Valid @RequestBody VehicleCreateDTO createDTO) {
        vehicleService.register(createDTO);
    }
}
