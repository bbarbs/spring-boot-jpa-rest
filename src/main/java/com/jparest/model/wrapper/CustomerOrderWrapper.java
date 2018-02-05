package com.jparest.model.wrapper;

import com.jparest.model.dto.CustomerDto;

public class CustomerOrderWrapper {

    private CustomerDto customer;
    private OrderResponseWrapper orderDetails;

    public CustomerOrderWrapper() {
    }

    public CustomerOrderWrapper(CustomerDto customer, OrderResponseWrapper orderDetails) {
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public OrderResponseWrapper getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderResponseWrapper orderDetails) {
        this.orderDetails = orderDetails;
    }
}
