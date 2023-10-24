package com.intuit.rentabike.service.impl;

import com.intuit.rentabike.dao.ReservationRepository;
import com.intuit.rentabike.dao.VehicleInventoryRepository;
import com.intuit.rentabike.enums.ReservationStatus;
import com.intuit.rentabike.enums.VehicleStatus;
import com.intuit.rentabike.exception.ReservationNotAvailableException;
import com.intuit.rentabike.exception.ReservationNotFoundException;
import com.intuit.rentabike.model.Bike;
import com.intuit.rentabike.reservation.Reservation;
import com.intuit.rentabike.service.BikeService;
import com.intuit.rentabike.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final VehicleInventoryRepository vehicleInventoryRepository;
    private final ReservationRepository reservationRepository;
    private final BikeService bikeService;

    @Autowired
    public ReservationServiceImpl(VehicleInventoryRepository vehicleInventoryRepository, ReservationRepository reservationRepository, BikeService bikeService) {
        this.vehicleInventoryRepository = vehicleInventoryRepository;
        this.reservationRepository = reservationRepository;
        this.bikeService = bikeService;
    }

    @Override
    public synchronized Reservation reserveBike(Reservation reservation) {
        boolean isBikeAvailable = true;
        LocalDate startDate = reservation.getStartDateTime().toLocalDate();
        while(!startDate.isAfter(reservation.getEndDataTime().toLocalDate())){
            var inventoryIndex = new VehicleInventoryRepository.VehicleInventoryIndex(reservation.getModel(),reservation.getStartDateTime().toLocalDate());
            if(!vehicleInventoryRepository.isBikeAvailable(inventoryIndex)){
                isBikeAvailable=false;
            }
            startDate = startDate.plusDays(1);
        }
        if(isBikeAvailable){
            Bike bikeAssigned = bikeService.getBikeByModel(reservation.getModel());
            reservation.setBikeId(bikeAssigned.getBikeId());
            reservation.setCreationDateTime(LocalDateTime.now());
            bikeAssigned.setVehicleStatus(VehicleStatus.RESERVED);
            bikeService.updateBike(bikeAssigned);
            startDate = reservation.getStartDateTime().toLocalDate();
            while(!startDate.isAfter(reservation.getEndDataTime().toLocalDate())){
                var inventoryIndex = new VehicleInventoryRepository.VehicleInventoryIndex(reservation.getModel(),reservation.getStartDateTime().toLocalDate());
                var vehicleInventory = vehicleInventoryRepository.get(inventoryIndex);
                vehicleInventory.setTotalReserved(vehicleInventory.getTotalReserved()+1);
                vehicleInventoryRepository.update(vehicleInventory);
                startDate = startDate.plusDays(1);
            }
            reservation.setReservationStatus(ReservationStatus.CONFIRMED);
            reservationRepository.save(reservation);
        }else{
            throw new ReservationNotAvailableException(reservation);
        }
        return reservation;
    }

    @Override
    public void returnBike(String reservationId) {
        Reservation reservation = reservationRepository.getReservationById(reservationId);
        Bike bike = bikeService.getBikeById(reservation.getBikeId());
        bike.setVehicleStatus(VehicleStatus.AVAILABLE);
        bikeService.updateBike(bike);
        LocalDate startDate = reservation.getStartDateTime().toLocalDate();
        while(!startDate.isAfter(reservation.getEndDataTime().toLocalDate())){
            var inventoryIndex = new VehicleInventoryRepository.VehicleInventoryIndex(reservation.getModel(),reservation.getStartDateTime().toLocalDate());
            var vehicleInventory = vehicleInventoryRepository.get(inventoryIndex);
            vehicleInventory.setTotalReserved(vehicleInventory.getTotalReserved()-1);
            vehicleInventoryRepository.update(vehicleInventory);
            startDate = startDate.plusDays(1);
        }
        reservationRepository.updateReservationStatus(reservationId,ReservationStatus.COMPLETED);
    }

    @Override
    public void cancelReservation(String reservationId) {
        Reservation reservation = reservationRepository.getReservationById(reservationId);
        Bike bike = bikeService.getBikeById(reservation.getBikeId());
        bike.setVehicleStatus(VehicleStatus.AVAILABLE);
        bikeService.updateBike(bike);
        LocalDate startDate = reservation.getStartDateTime().toLocalDate();
        while(!startDate.isAfter(reservation.getEndDataTime().toLocalDate())){
            var inventoryIndex = new VehicleInventoryRepository.VehicleInventoryIndex(reservation.getModel(),reservation.getStartDateTime().toLocalDate());
            var vehicleInventory = vehicleInventoryRepository.get(inventoryIndex);
            vehicleInventory.setTotalReserved(vehicleInventory.getTotalReserved()-1);
            vehicleInventoryRepository.update(vehicleInventory);
            startDate = startDate.plusDays(1);
        }
        reservationRepository.updateReservationStatus(reservationId,ReservationStatus.CANCELLED);
    }

    @Override
    public Reservation getReservationById(String reservationId) {
        return reservationRepository.getReservationById(reservationId);
    }
}
