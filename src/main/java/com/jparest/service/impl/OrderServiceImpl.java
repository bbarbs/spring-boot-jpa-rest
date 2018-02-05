package com.jparest.service.impl;

import com.jparest.exception.*;
import com.jparest.model.Customer;
import com.jparest.model.Orders;
import com.jparest.model.enums.OrderStatusEnum;
import com.jparest.repository.CustomerRepository;
import com.jparest.repository.ItemRepository;
import com.jparest.repository.OrderRepository;
import com.jparest.rest.patch.Patch;
import com.jparest.rest.patch.PatchEnum;
import com.jparest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Orders> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Orders addOrder(Long customerId, Orders orders) throws CustomerNotFoundException,
            ItemNotFoundException {
        Customer customer = this.customerRepository.findOne(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        // Set customer order.
        customer.setOrder(orders);
        // Persist order.
        return this.orderRepository.save(orders);
    }

    @Override
    public List<Orders> getOrdersByCustomerId(Long customerId) throws OrderNotFoundException {
        List<Orders> orders = this.orderRepository.findAllByCustomer_Id(customerId);
        if (orders == null || orders.size() == 0) {
            throw new OrderNotFoundException("Order not found");
        }
        return orders;
    }

    @Override
    public Orders getOrderById(Long orderId) throws OrderNotFoundException {
        Orders orders = this.orderRepository.findOne(orderId);
        if (orders == null) {
            throw new OrderNotFoundException("Order not found");
        }
        return orders;
    }

    @Override
    public void removeOrderById(Long orderId) throws OrderNotFoundException {
        if (!this.orderRepository.exists(orderId)) {
            throw new OrderNotFoundException("Order not found");
        }
        this.orderRepository.delete(orderId);
    }

    @Override
    public Orders updateOrderById(Long orderId, Orders orders) throws OrderNotFoundException {
        Orders order = this.orderRepository.findOne(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found");
        }
        // Update order.
        order.setOrderDate(orders.getOrderDate());
        order.setAmount(orders.getAmount());
        order.setQuantity(orders.getQuantity());
        order.setRemarks(orders.getRemarks());
        order.setStatus(orders.getStatus());
        return this.orderRepository.save(order);
    }

    @Override
    public Orders patchOrderById(Long orderId, Patch patch) throws OrderNotFoundException,
            PatchOperationNotSupported, PatchFieldNotMatchException {
        // Check patch operation.
        if (patch.getPatchEnum().equals(PatchEnum.REPLACE)) {
            // Get order.
            Orders orders = this.orderRepository.findOne(orderId);
            if (orders == null) {
                throw new OrderNotFoundException("Order not found");
            }
            // Check what field to patch.
            if (patch.getField().equals(Orders.ORDER_AMOUNT)) {
                orders.setAmount(BigDecimal.valueOf(Long.parseLong(patch.getValue())));
            } else if (patch.getField().equals(Orders.ORDER_DATE)) {
                orders.setOrderDate(new Date(Long.parseLong(patch.getValue())));
            } else if (patch.getField().equals(Orders.ORDER_QUANTITY)) {
                orders.setQuantity(Integer.valueOf(patch.getValue()));
            } else if (patch.getField().equals(Orders.ORDER_REMARKS)) {
                orders.setRemarks(patch.getValue());
            } else if (patch.getField().equals(Orders.ORDER_STATUS)) {
                orders.setStatus(OrderStatusEnum.valueOf(patch.getValue()));
            } else {
                throw new PatchFieldNotMatchException("Patch field " + patch.getField() + " not match");
            }
            // Update order.
            return this.orderRepository.save(orders);
        } else {
            throw new PatchOperationNotSupported("Patch operation not supported");
        }
    }

    @Override
    public List<Orders> getOrdersByStatus(OrderStatusEnum status) {
        return this.orderRepository.findAllByStatus(status);
    }
}
