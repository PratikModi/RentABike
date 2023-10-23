package com.intuit.rentabike.dao;

import com.intuit.rentabike.exception.CustomerNotFoundException;
import com.intuit.rentabike.model.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class CustomerRepository {
    private final Map<Integer, Customer> customers;
    private final AtomicInteger idGenerator;

    public CustomerRepository() {
        this.customers = new HashMap<>();
        this.idGenerator = new AtomicInteger(1);
    }

    public Customer save(Customer customer){
        int customerId = idGenerator.getAndIncrement();
        customer.setCustomerId(customerId);
        customers.put(customerId,customer);
        return customer;
    }

    public void deleteCustomerById(int customerId){
        if(!customers.containsKey(customerId) || !customers.get(customerId).isActive()){
            throw new CustomerNotFoundException(customerId);
        }
        Customer customer = customers.get(customerId);
        customer.setActive(Boolean.FALSE);
    }

    public Customer getCustomerById(int customerId){
        if(!customers.containsKey(customerId)){
            throw new CustomerNotFoundException(customerId);
        }
        return customers.get(customerId);
    }

    public void updateCustomer(Customer customer){
        if(!customers.containsKey(customer.getCustomerId())){
            throw new CustomerNotFoundException(customer.getCustomerId());
        }
        customers.put(customer.getCustomerId(), customer);
    }

}
