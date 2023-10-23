package com.intuit.rentabike.service.impl;

import com.intuit.rentabike.dao.VehicleCenterRepository;
import com.intuit.rentabike.model.VehicleCenter;
import com.intuit.rentabike.service.VehicleCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VehicleCenterServiceImpl implements VehicleCenterService {

    private final VehicleCenterRepository vehicleCenterRepository;

    @Autowired
    public VehicleCenterServiceImpl(VehicleCenterRepository vehicleCenterRepository) {
        this.vehicleCenterRepository = vehicleCenterRepository;
    }

    @Override
    public VehicleCenter addVehicleCenter(VehicleCenter vehicleCenter) {
        if(Objects.nonNull(vehicleCenter)){
            return vehicleCenterRepository.save(vehicleCenter);
        }else{
            throw new IllegalArgumentException("Vehicle Center can't be null");
        }
    }

    @Override
    public void updateVehicleCenter(VehicleCenter vehicleCenter) {
        if(Objects.nonNull(vehicleCenter)){
            vehicleCenterRepository.update(vehicleCenter);
        }else{
            throw new IllegalArgumentException("Vehicle Center can't be null");
        }
    }

    @Override
    public void deleteVehicleCenterById(String vehicleCenterId) {
        vehicleCenterRepository.deleteById(vehicleCenterId);
    }

    @Override
    public VehicleCenter getVehicleCenterById(String vehicleCenterId) {
        return vehicleCenterRepository.getById(vehicleCenterId);
    }
}
