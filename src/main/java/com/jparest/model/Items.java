package com.jparest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Items {

    public static final String ITEM_CODE = "code";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_DESCRIPTION = "description";

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Must not be null")
    private String code;

    @NotNull(message = "Must not be null")
    private String name;

    private String description;

    @ManyToMany(mappedBy = "items")
    List<Orders> orders = new ArrayList<>();

    public Items() {
    }

    public Items(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
