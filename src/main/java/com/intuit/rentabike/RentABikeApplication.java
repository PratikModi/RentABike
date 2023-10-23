package com.intuit.rentabike;

import com.intuit.rentabike.dao.VehicleInventoryRepository;
import com.intuit.rentabike.reservation.VehicleInventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Slf4j
public class RentABikeApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RentABikeApplication.class, args);
        CompletableFuture.runAsync(()->createVehicleInventory(context));
    }

    private static void createVehicleInventory(ApplicationContext context){
        try{
            VehicleInventoryRepository repository = context.getBean(VehicleInventoryRepository.class);
            log.info("Creating Vehicle Inventory.....");
            TimeUnit.SECONDS.sleep(2);
            VehicleInventory v1 = VehicleInventory.builder()
                    .inventoryId(1)
                    .price(BigDecimal.valueOf(100))
                    .model("Activa")
                    .make("Honda")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("1")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v1);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Activa").build()).toString());
            VehicleInventory v2 = VehicleInventory.builder()
                    .inventoryId(2)
                    .price(BigDecimal.valueOf(100))
                    .model("Activa")
                    .make("Honda")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("2")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v2);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Activa").build()).toString());
            VehicleInventory v3 = VehicleInventory.builder()
                    .inventoryId(3)
                    .price(BigDecimal.valueOf(100))
                    .model("Activa")
                    .make("Honda")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("3")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v3);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Activa").build()).toString());
            VehicleInventory v4 = VehicleInventory.builder()
                    .inventoryId(4)
                    .price(BigDecimal.valueOf(300))
                    .model("Unicorn")
                    .make("Honda")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("1")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v4);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Unicorn").build()).toString());
            VehicleInventory v5 = VehicleInventory.builder()
                    .inventoryId(5)
                    .price(BigDecimal.valueOf(300))
                    .model("Unicorn")
                    .make("Honda")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("2")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v5);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Unicorn").build()).toString());
            VehicleInventory v6 = VehicleInventory.builder()
                    .inventoryId(6)
                    .price(BigDecimal.valueOf(200))
                    .model("Shine")
                    .make("Hero")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("1")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v6);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Shine").build()).toString());
            VehicleInventory v7 = VehicleInventory.builder()
                    .inventoryId(5)
                    .price(BigDecimal.valueOf(350))
                    .model("Pulsor")
                    .make("Bajaj")
                    .date(LocalDateTime.of(LocalDate.of(2023,10,23), LocalTime.of(0,0,0)))
                    .vehicleCenterId("1")
                    .totalVehicle(1)
                    .totalReserved(0)
                    .build();
            repository.save(v7);
            log.info("CREATED");
            log.info(repository.get(VehicleInventoryRepository.VehicleInventoryIndex.builder().date(LocalDate.of(2023,10,23)).model("Pulsor").build()).toString());
        }catch (Exception e){
            log.error("Something went wrong while creating vehicle inventory",e);
        }

    }

}
