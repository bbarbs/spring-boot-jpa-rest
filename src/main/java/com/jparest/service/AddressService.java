package com.jparest.service;

import com.jparest.model.Address;
import com.jparest.rest.patch.Patch;

import java.util.List;

public interface AddressService {

    Address addCustomerAddress(Long customerId, Address address);

    Address updateCustomerAddress(Long addressId, Address address);

    Address patchCustomerAddress(Long addressId, Patch patch);

    List<Address> getCustomerAddresses(Long customerId);
}
