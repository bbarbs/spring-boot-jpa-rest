package com.jparest.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jparest.model.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto {

    @ApiModelProperty(hidden = true)
    private Long orderId;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @ApiModelProperty(required = true)
    private Date orderDate;

    @ApiModelProperty(required = true)
    private OrderStatusEnum orderStatus;

    @ApiModelProperty(required = true)
    private BigDecimal orderAmount;

    @ApiModelProperty(required = true)
    private Integer orderQuantity;

    @ApiModelProperty(required = true)
    private String orderRemarks;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }
}
