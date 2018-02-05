package com.jparest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jparest.model.enums.OrderStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    public static final String ORDER_DATE = "orderDate";
    public static final String ORDER_STATUS = "status";
    public static final String ORDER_AMOUNT = "amount";
    public static final String ORDER_QUANTITY = "quantity";
    public static final String ORDER_REMARKS = "remarks";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "order_date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @NotNull(message = "Must not be null")
    private BigDecimal amount;

    @NotNull(message = "Must not be null")
    private Integer quantity;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            // The owning side.
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id"
            ),
            // The non owning side.
            inverseJoinColumns = @JoinColumn(
                    name = "item_id", referencedColumnName = "id"
            )
    )
    @JsonIgnoreProperties("orders")
    private List<Items> items = new ArrayList<>();

    public Orders() {
    }

    public Orders(Date orderDate, OrderStatusEnum status, BigDecimal amount, Integer quantity, String remarks) {
        this.orderDate = orderDate;
        this.status = status;
        this.amount = amount;
        this.quantity = quantity;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        if (items != null && (!items.isEmpty() || items.size() != 0)) {
            for (Items item : items) {
                setItem(item);
            }
        }
    }

    /**
     * Add order to child entity items.
     *
     * @param item
     */
    public void setItem(Items item) {
        this.items.add(item);
        item.getOrders().add(this);
    }
}
