package com.intuit.rentabike.controller;

import com.intuit.rentabike.exception.VehicleNotFoundException;
import com.intuit.rentabike.model.Bike;
import com.intuit.rentabike.service.BikeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
public class BikeController {

    private final BikeService bikeService;
    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @ApiOperation(value = "createBike")
    @ApiResponses(value = {
            @ApiResponse(code=500, message="Server Error"),
            @ApiResponse(code=404, message="Resource Not Found"),
            @ApiResponse(code=400, message="Bad Request"),
            @ApiResponse(code=201, message="Successful Response")
    })
    @PostMapping(value = "/v1/bikes")
    public ResponseEntity<Bike> createBike(@RequestBody @NotNull Bike bike){
        Bike createdBike = bikeService.addBike(bike);
        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBike.getBikeId())
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,location).body(createdBike);
    }

    @PostMapping(value = "/v1/bikes/bulk",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createBike(@RequestBody @NotNull List<Bike> bikes){
        bikes.forEach(bikeService::addBike);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/v1/bikes/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Bike> getBikeById(@PathVariable @NotNull Integer id){
        log.info("Trying to fetch Bike with Id:{}",id);
        Bike bike;
        try {
            bike = bikeService.getBikeById(id);
        }catch(Exception e){
            throw new VehicleNotFoundException(id);
        }
        return ResponseEntity.ok(bike);
    }

    @PutMapping(value = "/v1/bikes/{id}")
    public ResponseEntity<Void> updateBikeById(@PathVariable @NotNull Integer id, @RequestBody @NotNull Bike bike){
        log.info("Trying to update Bike with Id:{}",id);
        bike.setBikeId(id);
        bikeService.updateBike(bike);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/v1/bikes/{id}")
    public ResponseEntity<Void> deleteBikeById(@PathVariable @NotNull Integer id){
        log.info("Trying to delete Bike with Id:{}",id);
        bikeService.deleteBikeById(id);
        return ResponseEntity.noContent().build();
    }

}
