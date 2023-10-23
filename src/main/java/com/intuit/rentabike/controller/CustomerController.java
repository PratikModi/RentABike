package com.intuit.rentabike.controller;

import com.intuit.rentabike.exception.CustomerNotFoundException;
import com.intuit.rentabike.model.Customer;
import com.intuit.rentabike.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/v1/customers",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> createCustomer(@RequestBody @NotNull Customer customer){
        Customer newCustomer = customerService.addCustomer(customer);
        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCustomer.getCustomerId())
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION,location).body(newCustomer);
    }

    @PostMapping(value = "/v1/customers/bulk",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createCustomer(@RequestBody @NotNull List<Customer> customers){
        customers.forEach(customerService::addCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/v1/customers/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> getCustomerById(@PathVariable @NotNull Integer id){
        log.info("Trying to fetch Customer with Id:{}",id);
        Customer customer;
        try {
            customer = customerService.getCustomerById(id);
        }catch(Exception e){
            throw new CustomerNotFoundException(id);
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/v1/customers/{id}")
    public ResponseEntity<Void> updateCustomerById(@PathVariable @NotNull Integer id, @RequestBody @NotNull Customer customer){
        log.info("Trying to update customer with Id:{}",id);
        customer.setCustomerId(id);
        customerService.updateCustomer(customer);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/v1/customers/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable @NotNull Integer id){
        log.info("Trying to delete customer with Id:{}",id);
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
