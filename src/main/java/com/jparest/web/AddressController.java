package com.jparest.web;

import com.jparest.mapper.AddressMapper;
import com.jparest.model.Address;
import com.jparest.model.dto.AddressDto;
import com.jparest.rest.ApiResponse;
import com.jparest.rest.patch.Patch;
import com.jparest.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressMapper mapper;

    /**
     * Get addresses by customer Id.
     *
     * @param customerId
     * @return
     */
    @ApiOperation(
            value = "Get addresses by customer Id"
    )
    @GetMapping(
            value = "/customers/{customerId}/addresses",
            produces = APPLICATION_JSON_VALUE
    )
    public List<AddressDto> getCustomerAddresses(@ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId) {
        List<Address> addresses = this.addressService.getCustomerAddresses(customerId);
        return this.mapper.mapToAddressDtoList(addresses);
    }

    /**
     * Add customer address.
     *
     * @param customerId
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Add customer address"
    )
    @PostMapping(
            value = "/customers/{customerId}/addresses",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ApiResponse<AddressDto> addCustomerAddress(
            @ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId,
            @ApiParam(value = "Address details", required = true) @RequestBody AddressDto dto) {
        Address address = this.addressService.addCustomerAddress(customerId, this.mapper.mapToAddress(dto));
        return new ApiResponse<>(HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.mapper.mapToAddressDto(address))
        );
    }

    /**
     * Update customer address.
     *
     * @param addressId
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Update customer address"
    )
    @PutMapping(
            value = "/addresses/{addressId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ApiResponse<AddressDto> updateCustomerAddress(
            @ApiParam(value = "Address Id", required = true) @PathVariable(name = "addressId") Long addressId,
            @ApiParam(value = "Address details", required = true) @RequestBody AddressDto dto) {
        Address address = this.addressService.updateCustomerAddress(addressId, this.mapper.mapToAddress(dto));
        return new ApiResponse<>(HttpStatus.OK.value(),
                HttpStatus.OK,
                Arrays.asList(this.mapper.mapToAddressDto(address)));
    }

    /**
     * Patch customer address.
     *
     * @param addressId
     * @param patch
     * @return
     */
    @ApiOperation(
            value = "Patch customer address"
    )
    @PatchMapping(
            value = "/addresses/{addressId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ApiResponse<AddressDto> patchCustomerAddress(
            @ApiParam(value = "Address Id", required = true) @PathVariable(name = "addressId") Long addressId,
            @ApiParam(value = "Address details", required = true) @RequestBody Patch patch) {
        Address address = this.addressService.patchCustomerAddress(addressId, patch);
        return new ApiResponse<>(HttpStatus.OK.value(),
                HttpStatus.OK,
                Arrays.asList(this.mapper.mapToAddressDto(address))
        );
    }
}
