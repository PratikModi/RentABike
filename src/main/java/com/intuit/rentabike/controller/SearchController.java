package com.intuit.rentabike.controller;

import com.intuit.rentabike.enums.VehicleType;
import com.intuit.rentabike.model.response.VehicleResponse;
import com.intuit.rentabike.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
public class SearchController {

    private final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping(value = "/v1/search/types")
    public ResponseEntity<List<VehicleResponse>> searchBikesByType(@RequestParam @NotNull VehicleType vehicleType){
        List<VehicleResponse> bikesByType = searchService.getVehiclesByType(vehicleType);
        return ResponseEntity.ok(bikesByType);
    }

    @GetMapping(value = "/v1/search/makes")
    public ResponseEntity<List<VehicleResponse>> searchBikesByMake(@RequestParam @NotNull String make){
        List<VehicleResponse> bikesByMake = searchService.getVehiclesByMake(make);
        return ResponseEntity.ok(bikesByMake);
    }

    @GetMapping(value = "/v1/search/models")
    public ResponseEntity<List<VehicleResponse>> searchBikesByModel(@RequestParam @NotNull String model){
        List<VehicleResponse> bikesByModel = searchService.getVehiclesByModel(model);
        return ResponseEntity.ok(bikesByModel);
    }
}
