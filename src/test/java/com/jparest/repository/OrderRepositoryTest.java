package com.jparest.repository;

import com.jparest.model.Customer;
import com.jparest.model.Items;
import com.jparest.model.Orders;
import com.jparest.model.enums.OrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testShouldAddCustomerOrder() {
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        Object customerId = this.entityManager.persistAndGetId(customer);
        // Set items.
        Items items = new Items();
        items.setCode("000");
        items.setName("Item");
        items.setDescription("Test");
        // Get added customer.
        Customer c = this.customerRepository.findOne(Long.valueOf(String.valueOf(customerId)));
        // Set order.
        Orders orders = new Orders();
        orders.setOrderDate(new Date());
        orders.setAmount(BigDecimal.valueOf(100));
        orders.setStatus(OrderStatusEnum.NEW);
        orders.setQuantity(1);
        orders.setRemarks("Test Order");
        orders.setItem(items);
        // Set order on customer.
        c.setOrder(orders);
        Orders newOrder = this.entityManager.persist(orders);
        // Assert.
        assertNotNull(newOrder);
        assertThat(newOrder.getCustomer()).isEqualTo(c);
        assertThat(newOrder.getItems().get(0)).isEqualTo(items);
    }
}
