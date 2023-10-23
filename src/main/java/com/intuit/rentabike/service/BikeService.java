package com.intuit.rentabike.service;

import com.intuit.rentabike.model.Bike;

public interface BikeService {
    Bike addBike(Bike bike);
    void updateBike(Bike bike);
    void deleteBikeById(int bikeId);
    Bike getBikeById(int bikeId);
    Bike getBikeByModel(String model);
}
