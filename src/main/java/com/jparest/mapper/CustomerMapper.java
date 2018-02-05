package com.jparest.mapper;

import com.jparest.model.Customer;
import com.jparest.model.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "id", source = "customerId")
    })
    Customer mapDTOtoEntity(CustomerDto source);

    @Mappings({
            @Mapping(target = "customerId", source = "id")
    })
    CustomerDto mapEntityToDTO(Customer source);

    List<Customer> mapDTOsToEntities(List<CustomerDto> sources);

    List<CustomerDto> mapEntitiesToDTOs(List<Customer> sources);
}
