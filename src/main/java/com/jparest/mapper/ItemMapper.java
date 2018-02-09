package com.jparest.mapper;

import com.jparest.model.Items;
import com.jparest.model.dto.ItemDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ItemMapper {

    @Mappings({
            @Mapping(target = "id", source = "itemId"),
            @Mapping(target = "code", source = "itemCode"),
            @Mapping(target = "name", source = "itemName"),
            @Mapping(target = "description", source = "itemDescription")
    })
    Items mapToItems(ItemDto source);

    @IterableMapping(elementTargetType = Items.class)
    List<Items> mapToItemsList(List<ItemDto> source);

    @Mappings({
            @Mapping(target = "itemId", source = "id"),
            @Mapping(target = "itemCode", source = "code"),
            @Mapping(target = "itemName", source = "name"),
            @Mapping(target = "itemDescription", source = "description")
    })
    ItemDto mapToItemDto(Items source);

    @IterableMapping(elementTargetType = ItemDto.class)
    List<ItemDto> mapToItemDto(List<Items> source);
}
