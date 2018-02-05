package com.jparest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Customer {

    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "Must not be null")
    private String firstname;

    @Column(name = "last_name")
    @NotNull(message = "Must not be null")
    private String lastname;

    // The non owning side.
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"customer", "id"}) // Ignore fields on other side.
    private Credentials credentials;

    // The non owning side.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"customer", "id"})
    private List<Address> addresses = new ArrayList<>();

    // The non owning side.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"customer"})
    private List<Orders> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
        if (credentials != null) {
            // Add the Customer to child entity Credentials
            credentials.setCustomer(this);
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        if (addresses != null && (!addresses.isEmpty() || addresses.size() != 0)) {
            for (Address address : addresses) {
                setAddress(address);
            }
        }
    }

    /**
     * Set the Customer to child entity Address.
     *
     * @param address
     */
    @ApiModelProperty(hidden = true) // Work around for the meantime to hide list in swagger ui.
    public void setAddress(Address address) {
        this.addresses.add(address);
        address.setCustomer(this);
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        if (orders != null && (!orders.isEmpty() || orders.size() != 0)) {
            for (Orders order : orders) {
                setOrder(order);
            }
        }
    }

    /**
     * Set customer to child entity order.
     *
     * @param order
     */
    public void setOrder(Orders order) {
        this.orders.add(order);
        order.setCustomer(this);
    }
}
