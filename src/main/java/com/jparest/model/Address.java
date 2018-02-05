package com.jparest.model;

import com.jparest.model.enums.AddressEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    public static final String ADDRESS_TYPE = "type";
    public static final String ADDRESS = "address";

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Must not be null")
    private AddressEnum type;

    @Column(length = 300)
    @NotNull(message = "Must not be null")
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address() {
    }

    public Address(AddressEnum type, String address) {
        this.type = type;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressEnum getType() {
        return type;
    }

    public void setType(AddressEnum type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //@JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
