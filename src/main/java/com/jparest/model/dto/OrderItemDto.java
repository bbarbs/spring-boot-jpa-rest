package com.jparest.model.dto;

import java.util.List;

public class OrderItemDto extends OrderDto {

    private List<ItemDto> items;

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
