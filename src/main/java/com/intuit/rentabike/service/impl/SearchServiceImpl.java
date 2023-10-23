package com.intuit.rentabike.service.impl;

import com.intuit.rentabike.dao.BikeRepository;
import com.intuit.rentabike.enums.VehicleType;
import com.intuit.rentabike.model.Bike;
import com.intuit.rentabike.model.response.VehicleResponse;
import com.intuit.rentabike.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("Duplicate")
public class SearchServiceImpl implements SearchService {

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public List<VehicleResponse> getVehiclesByType(VehicleType vehicleType) {
        List<VehicleResponse> vehicleResponse = new ArrayList<>();
        var allBikesByType = bikeRepository.getAllBikes().stream().filter(e->vehicleType.equals(e.getVehicleType()) && e.isActive()).toList();
        var allBikesByMakeModel = allBikesByType.stream().collect(Collectors.groupingBy(Bike::getMake,Collectors.groupingBy(Bike::getModel)));
        allBikesByMakeModel.forEach((key, value) -> {
            VehicleResponse.VehicleResponseBuilder builder = VehicleResponse.builder();
            builder.make(key);
            value.forEach((model,bike)-> {
                    builder.model(model);
                    builder.vehicleCenterIds(bike.stream().map(Bike::getVehicleCenterId).toList());
                    var price = bike.stream().findAny().orElseGet(null);
                    builder.price(price!=null?price.getPrice():null);
                    builder.vehicleType(price!=null?price.getVehicleType():VehicleType.MOTORCYCLE);
            });
            vehicleResponse.add(builder.build());
        });
        return vehicleResponse;
    }

    @Override
    public List<VehicleResponse> getVehiclesByModel(String model) {
        List<VehicleResponse> vehicleResponse = new ArrayList<>();
        var allBikesByType = bikeRepository.getAllBikes().stream().filter(e->e.getModel().equalsIgnoreCase(model) && e.isActive()).toList();
        var allBikesByModel = allBikesByType.stream().collect(Collectors.groupingBy(Bike::getModel));
        allBikesByModel.forEach((key,bike)->{
            VehicleResponse.VehicleResponseBuilder builder = VehicleResponse.builder();
            builder.model(model);
            builder.vehicleCenterIds(bike.stream().map(Bike::getVehicleCenterId).toList());
            var price = bike.stream().findAny().orElseGet(null);
            builder.price(price!=null?price.getPrice():null);
            builder.make(price!=null?price.getMake():null);
            builder.vehicleType(price!=null?price.getVehicleType():VehicleType.MOTORCYCLE);
            vehicleResponse.add(builder.build());
        });
        return vehicleResponse;
    }

    @Override
    public List<VehicleResponse> getVehiclesByMake(String make) {
        List<VehicleResponse> vehicleResponse = new ArrayList<>();
        var allBikesByType = bikeRepository.getAllBikes().stream().filter(e->e.getMake().equalsIgnoreCase(make) && e.isActive()).toList();
        var allBikesByMakeModel = allBikesByType.stream().collect(Collectors.groupingBy(Bike::getMake,Collectors.groupingBy(Bike::getModel)));
        allBikesByMakeModel.forEach((key, value) -> {
            VehicleResponse.VehicleResponseBuilder builder = VehicleResponse.builder();
            builder.make(key);
            value.forEach((model,bike)-> {
                builder.model(model);
                builder.vehicleCenterIds(bike.stream().map(Bike::getVehicleCenterId).toList());
                var price = bike.stream().findAny().orElseGet(null);
                builder.price(price!=null?price.getPrice():null);
                builder.vehicleType(price!=null?price.getVehicleType():VehicleType.MOTORCYCLE);
            });
            vehicleResponse.add(builder.build());
        });
        return vehicleResponse;
    }
}
