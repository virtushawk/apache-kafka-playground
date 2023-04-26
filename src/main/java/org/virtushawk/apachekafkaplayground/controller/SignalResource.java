package org.virtushawk.apachekafkaplayground.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.virtushawk.apachekafkaplayground.entity.VehicleSignalDTO;
import org.virtushawk.apachekafkaplayground.service.VehicleService;

@RestController
@RequestMapping("/signal")
public class SignalResource {

    private final VehicleService vehicleService;

    public SignalResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updatePosition(@Valid @RequestBody VehicleSignalDTO signalDTO) {
        vehicleService.updatePosition(signalDTO);
    }
}
