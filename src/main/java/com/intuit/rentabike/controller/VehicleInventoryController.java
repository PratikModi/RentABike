package com.intuit.rentabike.controller;

import com.intuit.rentabike.dao.VehicleInventoryRepository;
import com.intuit.rentabike.reservation.VehicleInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleInventoryController {

    private final VehicleInventoryRepository repository;

    @Autowired
    public VehicleInventoryController(VehicleInventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/v1/vehicles/inventory")
    public ResponseEntity<List<VehicleInventory>> getAllVehicleInventory(){
        var inventory = repository.getAllInventory();
        return ResponseEntity.ok(inventory);
    }

}
