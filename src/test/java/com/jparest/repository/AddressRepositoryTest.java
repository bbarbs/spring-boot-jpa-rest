package com.jparest.repository;

import com.jparest.model.Address;
import com.jparest.model.Customer;
import com.jparest.model.enums.AddressEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void testShouldFindAddressByCustomerId() {
        Address address = new Address();
        address.setType(AddressEnum.PRIMARY);
        address.setAddress("Area 51");
        // Set customer.
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        customer.setAddress(address);
        Object obj = this.entityManager.persistAndGetId(customer);
        // Get address by customer id.
        Address result = this.addressRepository.findByCustomer_Id(Long.valueOf(String.valueOf(obj)));
        assertNotNull(result);
        assertThat(result.getAddress()).isEqualTo(address.getAddress());
    }

    @Test
    public void testShouldFindAllAddressByCustomerId() {
        Address address = new Address();
        address.setType(AddressEnum.PRIMARY);
        address.setAddress("Area 51");
        // Set customer.
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        customer.setAddress(address);
        Object obj = this.entityManager.persistAndGetId(customer);
        // Get address by customer id.
        List<Address> list = this.addressRepository.findAllByCustomer_Id(Long.valueOf(String.valueOf(obj)));
        assertNotNull(list);
        assertThat(list.get(0).getAddress()).isEqualTo(address.getAddress());
    }
}
