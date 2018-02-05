package com.jparest.service;

import com.jparest.model.Customer;
import com.jparest.rest.patch.Patch;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Customer getCustomerById(Long customerId);

    Customer addCustomer(Customer entity);

    Customer updateCustomer(Long customerId, Customer customer);

    void deleteCustomerById(Long customerId);

    Customer patchCustomer(Long customerId, Patch patch);
}
