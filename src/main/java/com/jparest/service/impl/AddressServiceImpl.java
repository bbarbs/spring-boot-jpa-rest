package com.jparest.service.impl;

import com.jparest.exception.AddressNotFoundException;
import com.jparest.exception.CustomerNotFoundException;
import com.jparest.exception.PatchFieldNotMatchException;
import com.jparest.exception.PatchOperationNotSupported;
import com.jparest.model.Address;
import com.jparest.model.Customer;
import com.jparest.model.enums.AddressEnum;
import com.jparest.repository.AddressRepository;
import com.jparest.repository.CustomerRepository;
import com.jparest.rest.patch.Patch;
import com.jparest.rest.patch.PatchEnum;
import com.jparest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

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
