package com.jparest.service.impl;

import com.jparest.exception.*;
import com.jparest.model.Address;
import com.jparest.model.Credentials;
import com.jparest.model.Customer;
import com.jparest.model.enums.AddressEnum;
import com.jparest.repository.AddressRepository;
import com.jparest.repository.CredentialsRepository;
import com.jparest.repository.CustomerRepository;
import com.jparest.rest.patch.Patch;
import com.jparest.rest.patch.PatchEnum;
import com.jparest.service.AddressService;
import com.jparest.service.CredentialService;
import com.jparest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService, AddressService, CredentialService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    AddressRepository addressRepository;

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

    @Override
    public Credentials addCustomerCredentials(Long customerId, Credentials credentials) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findOne(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        // Check if username exist.
        if (this.isUsernameExists(credentials.getUsername())) {
            throw new UsernameExistException("Username already exist");
        }
        // Add customer credentials.
        customer.setCredentials(credentials);
        return this.credentialsRepository.save(credentials);
    }

    @Override
    public Credentials updateCustomerCredentials(Long credentialsId, Credentials credentials) throws CredentialsNotFoundException {
        Credentials res = this.credentialsRepository.findOne(credentialsId);
        if (res == null) {
            throw new CredentialsNotFoundException("Credentials not found");
        }
        res.setUsername(credentials.getUsername());
        res.setPassword(credentials.getPassword());
        return this.credentialsRepository.save(res);
    }

    @Override
    public Credentials patchCustomerCredentials(Long credentialsId, Patch patch) throws PatchOperationNotSupported,
            CredentialsNotFoundException, PatchFieldNotMatchException {
        // Check patch operation.
        if (patch.getPatchEnum().equals(PatchEnum.REPLACE)) {
            // Get customer credentials.
            Credentials credentials = this.credentialsRepository.findOne(credentialsId);
            if (credentials == null) {
                throw new CredentialsNotFoundException("Credentials not found");
            }
            // Check what field to update.
            if (patch.getField().equals(Credentials.USERNAME)) {
                credentials.setUsername(patch.getValue());
            } else if (patch.getField().equals(Credentials.PASSWORD)) {
                credentials.setPassword(patch.getValue());
            } else {
                throw new PatchFieldNotMatchException("Patch field (" + patch.getField() + ") not match");
            }
            // Update credentials.
            return this.credentialsRepository.save(credentials);
        } else {
            throw new PatchOperationNotSupported("Patch operation supported");
        }
    }

    @Override
    public List<Credentials> getCustomerCredentials(Long customerId) throws CustomerNotFoundException {
        boolean exists = this.customerRepository.exists(customerId);
        if (!exists) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return this.credentialsRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public boolean isUsernameExists(String username) {
        Credentials res = this.credentialsRepository.findByUsername(username);
        if (res == null) {
            return false;
        }
        return true;
    }

    @Override
    public Address addCustomerAddress(Long customerId, Address address) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findOne(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        // Set address.
        customer.setAddress(address);
        return this.addressRepository.save(address);
    }

    @Override
    public Address updateCustomerAddress(Long addressId, Address address) throws AddressNotFoundException {
        Address addr = this.addressRepository.findOne(addressId);
        if (addr == null) {
            throw new AddressNotFoundException("Address not found");
        }
        // Update address.
        addr.setType(address.getType());
        addr.setAddress(address.getAddress());
        return this.addressRepository.save(addr);
    }

    @Override
    public Address patchCustomerAddress(Long addressId, Patch patch) throws PatchOperationNotSupported,
            AddressNotFoundException, PatchFieldNotMatchException {
        // Check patch operation.
        if (patch.getPatchEnum().equals(PatchEnum.REPLACE)) {
            // Get customer addresses.
            Address address = this.addressRepository.findOne(addressId);
            if (address == null) {
                throw new AddressNotFoundException("Address not found");
            }
            // Check what field to update.
            if (patch.getField().equals(Address.ADDRESS_TYPE)) {
                address.setType(AddressEnum.valueOf(patch.getValue()));
            } else if (patch.getField().equals(Address.ADDRESS)) {
                address.setAddress(patch.getValue());
            } else {
                throw new PatchFieldNotMatchException("Patch field (" + patch.getField() + ") not match");
            }
            // Update address.
            return this.addressRepository.save(address);
        } else {
            throw new PatchOperationNotSupported("Patch operation supported");
        }
    }

    @Override
    public List<Address> getCustomerAddresses(Long customerId) throws CustomerNotFoundException {
        boolean exists = this.customerRepository.exists(customerId);
        if (!exists) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return this.addressRepository.findAllByCustomer_Id(customerId);
    }
}
