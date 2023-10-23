package com.intuit.rentabike.service;

import com.intuit.rentabike.model.Customer;

public interface CustomerService {
    Customer addCustomer(Customer user);
    void updateCustomer(Customer user);
    void deleteCustomerById(int userId);
    Customer getCustomerById(int userId);
}
