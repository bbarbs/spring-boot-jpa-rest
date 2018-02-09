package com.jparest.mapper;

import com.jparest.model.Customer;
import com.jparest.model.dto.CustomerDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "id", source = "customerId")
    })
    Customer mapToCustomer(CustomerDto source);

    @IterableMapping(elementTargetType = Customer.class)
    List<Customer> mapToCustomerList(List<CustomerDto> sources);

    @Mappings({
            @Mapping(target = "customerId", source = "id")
    })
    CustomerDto mapToCustomerDto(Customer source);

    @IterableMapping(elementTargetType = CustomerDto.class)
    List<CustomerDto> mapToCustomerDtoList(List<Customer> sources);
}
