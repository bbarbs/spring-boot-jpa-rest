package com.jparest.service;

import com.jparest.model.Orders;
import com.jparest.model.enums.OrderStatusEnum;
import com.jparest.rest.patch.Patch;

import java.util.List;

public interface OrderService {

    List<Orders> getAllOrders();

    Orders addOrder(Long customerId, Orders orders);

    List<Orders> getOrdersByCustomerId(Long customerId);

    Orders getOrderById(Long orderId);

    void removeOrderById(Long orderId);

    Orders updateOrderById(Long orderId, Orders orders);

    Orders patchOrderById(Long orderId, Patch patch);

    List<Orders> getOrdersByStatus(OrderStatusEnum status);
}
