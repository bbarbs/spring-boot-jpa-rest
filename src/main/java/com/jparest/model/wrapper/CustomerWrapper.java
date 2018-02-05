package com.jparest.model.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jparest.model.dto.AddressDto;
import com.jparest.model.dto.CredentialsDto;
import com.jparest.model.dto.CustomerDto;

import java.util.List;

public class CustomerWrapper {

    private CustomerDto customerDetails;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CredentialsDto credentials;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AddressDto> addresses;

    public CustomerWrapper() {
    }

    public CustomerWrapper(CustomerDto customerDetails, CredentialsDto credentials, List<AddressDto> addresses) {
        this.customerDetails = customerDetails;
        this.credentials = credentials;
        this.addresses = addresses;
    }

    public CustomerDto getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDto customerDetails) {
        this.customerDetails = customerDetails;
    }

    public CredentialsDto getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsDto credentials) {
        this.credentials = credentials;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }
}
