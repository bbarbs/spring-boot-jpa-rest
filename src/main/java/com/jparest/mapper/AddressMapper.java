package com.jparest.mapper;

import com.jparest.model.Address;
import com.jparest.model.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({
            @Mapping(target = "id", source = "addressId"),
            @Mapping(target = "type", source = "addressType"),
            @Mapping(target = "address", source = "address")
    })
    Address mapDTOtoEntity(AddressDto source);

    @Mappings({
            @Mapping(target = "addressId", source = "id"),
            @Mapping(target = "addressType", source = "type"),
            @Mapping(target = "address", source = "address")
    })
    AddressDto mapEntityToDTO(Address source);

    List<Address> mapDTOsToEntities(List<AddressDto> sources);

    List<AddressDto> mapEntitiesToDTOs(List<Address> sources);
}
