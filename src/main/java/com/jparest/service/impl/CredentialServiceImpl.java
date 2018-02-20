package com.jparest.service.impl;

import com.jparest.exception.*;
import com.jparest.model.Credentials;
import com.jparest.model.Customer;
import com.jparest.repository.CredentialsRepository;
import com.jparest.repository.CustomerRepository;
import com.jparest.rest.patch.Patch;
import com.jparest.rest.patch.PatchEnum;
import com.jparest.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

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
}
