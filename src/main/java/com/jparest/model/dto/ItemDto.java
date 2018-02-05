package com.jparest.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class ItemDto {

    @ApiModelProperty(hidden = true)
    private Long itemId;

    @ApiModelProperty(required = true)
    private String itemCode;

    @ApiModelProperty(required = true)
    private String itemName;

    @ApiModelProperty(required = true)
    private String itemDescription;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
