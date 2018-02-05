package com.jparest.service;

import com.jparest.model.Customer;
import com.jparest.model.Items;
import com.jparest.model.Orders;
import com.jparest.model.enums.OrderStatusEnum;
import com.jparest.repository.CustomerRepository;
import com.jparest.repository.ItemRepository;
import com.jparest.repository.OrderRepository;
import com.jparest.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void testShouldAddCustomerOrder() {
        Customer customer = new Customer();
        customer.setFirstname("Test");
        customer.setLastname("Test");
        // Stub
        given(this.customerRepository.findOne(anyLong())).willReturn(customer);
        // Set order.
        Orders orders = new Orders();
        orders.setRemarks("remarks");
        orders.setQuantity(1);
        orders.setStatus(OrderStatusEnum.NEW);
        orders.setAmount(BigDecimal.valueOf(100));
        orders.setOrderDate(new Date());
        orders.setItems(Arrays.asList(new Items()));
        // Set customer order.
        customer.setOrder(orders);
        when(this.orderRepository.save(orders)).thenReturn(orders);
        // Assert
        Orders result = this.orderService.addOrder(1L, orders);
        assertNotNull(result);
        assertThat(result.getCustomer()).isEqualTo(customer);
        assertThat(result.getAmount()).isEqualTo(orders.getAmount());
    }
}
