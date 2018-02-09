package com.jparest.model.request;

import com.jparest.model.dto.OrderDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class OrderRequest {

    private OrderDto order;

    @ApiModelProperty(required = true)
    private List<Long> itemIds;

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
