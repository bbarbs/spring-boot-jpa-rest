package com.jparest.model.wrapper;

import com.jparest.model.dto.ItemDto;
import com.jparest.model.dto.OrderDto;

import java.util.List;

public class OrderResponseWrapper {

    private OrderDto order;
    private List<ItemDto> items;

    public OrderResponseWrapper() {
    }

    public OrderResponseWrapper(OrderDto order, List<ItemDto> items) {
        this.order = order;
        this.items = items;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
