package com.jparest.service.impl;

import com.jparest.exception.CustomerNotFoundException;
import com.jparest.exception.PatchFieldNotMatchException;
import com.jparest.exception.PatchOperationNotSupported;
import com.jparest.model.Customer;
import com.jparest.repository.CustomerRepository;
import com.jparest.rest.patch.Patch;
import com.jparest.rest.patch.PatchEnum;
import com.jparest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findOne(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customer;
    }

    @Override
    public Customer addCustomer(Customer entity) {
        return this.customerRepository.saveAndFlush(entity);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) throws CustomerNotFoundException {
        Customer res = this.customerRepository.findOne(customerId);
        if (res == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        // Update customer.
        res.setFirstname(customer.getFirstname());
        res.setLastname(customer.getLastname());
        return this.customerRepository.save(res);
    }

    @Override
    public void deleteCustomerById(Long customerId) throws CustomerNotFoundException {
        if (!this.customerRepository.exists(customerId)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        this.customerRepository.delete(customerId);
    }

    @Override
    public Customer patchCustomer(Long customerId, Patch patch) throws PatchOperationNotSupported,
            CustomerNotFoundException, PatchFieldNotMatchException {
        // Check patch operation.
        if (patch.getPatchEnum().equals(PatchEnum.REPLACE)) {
            // Get customer.
            Customer customer = this.customerRepository.findOne(customerId);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            // Check what field to update.
            if (patch.getField().equals(Customer.FIRSTNAME)) {
                customer.setFirstname(patch.getValue());
            } else if (patch.getField().equals(Customer.LASTNAME)) {
                customer.setLastname(patch.getValue());
            } else {
                throw new PatchFieldNotMatchException("Patch field (" + patch.getField() + ") not match");
            }
            // Update customer.
            return this.customerRepository.save(customer);
        } else {
            throw new PatchOperationNotSupported("Patch operation supported");
        }
    }
}
