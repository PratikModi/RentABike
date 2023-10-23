package com.intuit.rentabike.service.impl;

import com.intuit.rentabike.dao.CustomerRepository;
import com.intuit.rentabike.model.Customer;
import com.intuit.rentabike.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer user) {
        if(Objects.nonNull(user)) {
            return customerRepository.save(user);
        }else
            throw new IllegalArgumentException("Customer can't be null");
    }

    @Override
    public void updateCustomer(Customer user) {
        if(Objects.nonNull(user)) {
            customerRepository.updateCustomer(user);
        }else
            throw new IllegalArgumentException("Customer can't be null");
    }

    @Override
    public void deleteCustomerById(int userId) {
        customerRepository.deleteCustomerById(userId);
    }

    @Override
    public Customer getCustomerById(int userId) {
        return customerRepository.getCustomerById(userId);
    }
}
