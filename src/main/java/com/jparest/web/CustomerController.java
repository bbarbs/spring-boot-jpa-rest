package com.jparest.web;

import com.jparest.mapper.AddressMapper;
import com.jparest.mapper.CredentialsMapper;
import com.jparest.mapper.CustomerMapper;
import com.jparest.model.Customer;
import com.jparest.model.dto.CustomerDto;
import com.jparest.rest.ApiResponse;
import com.jparest.rest.patch.Patch;
import com.jparest.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/v1/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CredentialsMapper credentialsMapper;

    /**
     * Get all list of customer
     *
     * @return
     */
    @ApiOperation(
            value = "Get all customers"
    )
    @GetMapping(
            value = "/customers",
            produces = APPLICATION_JSON_VALUE
    )
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = this.customerService.getAllCustomer();
        return this.customerMapper.mapToCustomerDtoList(customers);
    }

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiOperation(
            value = "Get customer by Id"
    )
    @GetMapping(
            value = "/customers/{customerId}",
            produces = APPLICATION_JSON_VALUE
    )
    public CustomerDto getCustomerById(@ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId) {
        Customer customer = this.customerService.getCustomerById(customerId);
        return this.customerMapper.mapToCustomerDto(customer);
    }

    /**
     * Add customer.
     *
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Add new customer"
    )
    @PostMapping(
            value = "/customers",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerDto> addNewCustomer(@ApiParam(value = "Customer details", required = true) @RequestBody CustomerDto dto) {
        Customer customer = this.customerService.addCustomer(this.customerMapper.mapToCustomer(dto));
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.customerMapper.mapToCustomerDto(customer))
        );
    }

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiOperation(
            value = "Delete customer by Id"
    )
    @DeleteMapping(
            value = "/customers/{customerId}",
            produces = TEXT_PLAIN_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteCustomerById(@ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId) {
        this.customerService.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update customer details.
     *
     * @param customerId
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Update customer"
    )
    @PutMapping(
            value = "/customers/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerDto> updateCustomer(
            @ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId,
            @ApiParam(value = "Customer details", required = true) @RequestBody CustomerDto dto) {
        Customer customer = this.customerService.updateCustomer(customerId, this.customerMapper.mapToCustomer(dto));
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.customerMapper.mapToCustomerDto(customer))
        );
    }

    /**
     * Patch customer details.
     *
     * @param customerId
     * @param patch
     * @return
     */
    @ApiOperation(
            value = "Patch customer"
    )
    @PatchMapping(
            value = "/customer/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerDto> patchCustomer(
            @ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId,
            @ApiParam(value = "Patch details", required = true) @RequestBody Patch patch) {
        Customer customer = this.customerService.patchCustomer(customerId, patch);
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.customerMapper.mapToCustomerDto(customer))
        );
    }
}
