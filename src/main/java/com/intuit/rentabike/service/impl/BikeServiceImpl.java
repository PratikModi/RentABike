package com.intuit.rentabike.service.impl;

import com.intuit.rentabike.dao.BikeRepository;
import com.intuit.rentabike.enums.VehicleStatus;
import com.intuit.rentabike.model.Bike;
import com.intuit.rentabike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;
    @Autowired
    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Bike addBike(Bike bike) {
        if(Objects.nonNull(bike)){
            return bikeRepository.save(bike);
        }else{
            throw new IllegalArgumentException("Bike can't be null");
        }
    }

    @Override
    public void updateBike(Bike bike) {
        if(Objects.nonNull(bike)){
            bikeRepository.updateBike(bike);
        }else{
            throw new IllegalArgumentException("Bike can't be null");
        }
    }

    @Override
    public void deleteBikeById(int bikeId) {
        bikeRepository.deleteBikeById(bikeId);
    }

    @Override
    public Bike getBikeById(int bikeId) {
        return bikeRepository.getBikeById(bikeId);
    }

    @Override
    public Bike getBikeByModel(String model) {
        return bikeRepository.getAllBikes().stream().filter(e->e.isActive() && VehicleStatus.AVAILABLE.equals(e.getVehicleStatus()) && e.getModel().equalsIgnoreCase(model))
                .findFirst().get();
    }
}
