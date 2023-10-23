package com.intuit.rentabike.controller;

import com.intuit.rentabike.exception.VehicleCenterNotFoundException;
import com.intuit.rentabike.model.VehicleCenter;
import com.intuit.rentabike.service.VehicleCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
public class VehicleCenterController {

    private final VehicleCenterService vehicleCenterService;
    @Autowired
    public VehicleCenterController(VehicleCenterService vehicleCenterService) {
        this.vehicleCenterService = vehicleCenterService;
    }

    @PostMapping(value = "/v1/vehicleCenters")
    public ResponseEntity<VehicleCenter> createVehicleCenter(@RequestBody @NotNull VehicleCenter vehicleCenter){
        VehicleCenter createdVehicleCenter = vehicleCenterService.addVehicleCenter(vehicleCenter);
        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVehicleCenter.getVehicleCenterId())
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,location).body(createdVehicleCenter);
    }

    @PostMapping(value = "/v1/vehicleCenters/bulk")
    public ResponseEntity<Void> createVehicleCenter(@RequestBody @NotNull List<VehicleCenter> vehicleCenters){
        vehicleCenters.forEach(vehicleCenterService::addVehicleCenter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/v1/vehicleCenters/{id}")
    public ResponseEntity<VehicleCenter> getVehicleCenterById(@PathVariable @NotNull String id){
        log.info("Trying to fetch Vehicle Center with Id:{}",id);
        VehicleCenter center=null;
        try {
            center = vehicleCenterService.getVehicleCenterById(id);
        }catch(Exception e){
            throw new VehicleCenterNotFoundException(id);
        }
        return ResponseEntity.ok(center);
    }

    @PutMapping(value = "/v1/vehicleCenters/{id}")
    public ResponseEntity<Void> updateVehicleCenterById(@PathVariable @NotNull String id, @RequestBody @NotNull VehicleCenter vehicleCenter){
        log.info("Trying to update Vehicle Center with Id:{}",id);
        vehicleCenter.setVehicleCenterId(id);
        vehicleCenterService.updateVehicleCenter(vehicleCenter);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/v1/vehicleCenters/{id}")
    public ResponseEntity<Void> deleteVehicleCenterById(@PathVariable @NotNull String id){
        log.info("Trying to delete Vehicle Center with Id:{}",id);
        vehicleCenterService.deleteVehicleCenterById(id);
        return ResponseEntity.noContent().build();
    }
}
