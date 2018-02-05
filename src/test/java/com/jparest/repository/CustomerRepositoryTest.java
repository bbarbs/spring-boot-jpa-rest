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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testShouldAddCustomer() {
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        Customer result = this.entityManager.persist(customer);
        assertNotNull(result);
        assertThat(customer.getFirstname()).isEqualTo(result.getFirstname());
    }

    @Test
    public void testShouldAddCustomerAddress() {
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        Object obj = this.entityManager.persistAndGetId(customer);
        // Find customer.
        Customer c = this.customerRepository.findOne(Long.valueOf(String.valueOf(obj)));
        Address address = new Address();
        address.setType(AddressEnum.PRIMARY);
        address.setAddress("Area 51");
        // Set customer address.
        c.setAddress(address);
        Customer result = this.entityManager.merge(c);
        assertNotNull(result);
        for (Address a : result.getAddresses()) {
            assertThat(a.getAddress()).isEqualTo(address.getAddress());
            assertThat(a.getType()).isEqualTo(address.getType());
        }
    }
}
