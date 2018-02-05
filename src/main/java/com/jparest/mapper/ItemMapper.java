package com.jparest.mapper;

import com.jparest.model.Items;
import com.jparest.model.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mappings({
            @Mapping(target = "id", source = "itemId"),
            @Mapping(target = "code", source = "itemCode"),
            @Mapping(target = "name", source = "itemName"),
            @Mapping(target = "description", source = "itemDescription")
    })
    Items mapDTOtoEntity(ItemDto source);

    @Mappings({
            @Mapping(target = "itemId", source = "id"),
            @Mapping(target = "itemCode", source = "code"),
            @Mapping(target = "itemName", source = "name"),
            @Mapping(target = "itemDescription", source = "description")
    })
    ItemDto mapEntityToDTO(Items source);

    List<Items> mapDTOsToEntities(List<ItemDto> source);

    List<ItemDto> mapEntitiesToDTOs(List<Items> source);
}
