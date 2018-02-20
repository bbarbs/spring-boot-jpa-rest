package com.jparest.service;

import com.jparest.model.Address;
import com.jparest.model.Credentials;
import com.jparest.model.Customer;
import com.jparest.model.enums.AddressEnum;
import com.jparest.repository.AddressRepository;
import com.jparest.repository.CredentialsRepository;
import com.jparest.repository.CustomerRepository;
import com.jparest.service.impl.AddressServiceImpl;
import com.jparest.service.impl.CredentialServiceImpl;
import com.jparest.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @InjectMocks
    CredentialServiceImpl credentialService;

    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    CredentialsRepository credentialsRepository;

    @Test
    public void testShouldAddCustomer() {
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        given(this.customerRepository.saveAndFlush(customer)).willReturn(customer);
        Customer c = this.customerService.addCustomer(customer);
        assertNotNull(c);
        assertThat(c.getFirstname()).isEqualTo(customer.getFirstname());
    }

    @Test
    public void testShouldDeleteCustomer() {
        given(this.customerRepository.exists(anyLong())).willReturn(true);
        this.customerService.deleteCustomerById(1L);
        verify(this.customerRepository, times(1)).delete(1L);
    }

    @Test
    public void testShouldAddCustomerCredentials() {
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        given(this.customerRepository.findOne(anyLong())).willReturn(customer);
        // Set credentials
        Credentials credentials = new Credentials();
        credentials.setUsername("user");
        credentials.setPassword("pass");
        customer.setCredentials(credentials);
        when(this.credentialsRepository.save(credentials)).thenReturn(credentials);
        Credentials c = this.credentialService.addCustomerCredentials(1L, credentials);
        assertNotNull(c);
        assertThat(c.getCustomer()).isEqualTo(customer);
        assertThat(c.getUsername()).isEqualTo(credentials.getUsername());
    }

    @Test
    public void testShouldAddCustomerAddress() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("Test");
        customer.setLastname("Test");
        given(this.customerRepository.findOne(anyLong())).willReturn(customer);
        // Set address.
        Address address = new Address();
        address.setId(1L);
        address.setType(AddressEnum.PRIMARY);
        address.setAddress("Test");
        customer.setAddress(address);
        when(this.addressRepository.save(address)).thenReturn(address);
        Address a = this.addressService.addCustomerAddress(1L, address);
        assertNotNull(a);
        assertThat(a.getCustomer()).isEqualTo(customer);
        assertThat(a.getAddress()).isEqualTo(address.getAddress());
    }
}
