package com.jparest.model.dto;

import com.jparest.model.enums.AddressEnum;
import io.swagger.annotations.ApiModelProperty;

public class AddressDto {

    @ApiModelProperty(hidden = true)
    private Long addressId;

    @ApiModelProperty(required = true)
    private AddressEnum addressType;

    @ApiModelProperty(required = true)
    private String address;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public AddressEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressEnum addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
