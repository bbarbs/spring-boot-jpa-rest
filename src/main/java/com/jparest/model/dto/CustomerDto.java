package com.jparest.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class CustomerDto {

    @ApiModelProperty(hidden = true)
    private Long customerId;

    @ApiModelProperty(required = true)
    private String firstname;

    @ApiModelProperty(required = true)
    private String lastname;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
