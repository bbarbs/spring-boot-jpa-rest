package com.jparest.mapper;

import com.jparest.model.Orders;
import com.jparest.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", source = "orderId"),
            @Mapping(target = "amount", source = "orderAmount"),
            @Mapping(target = "quantity", source = "orderQuantity"),
            @Mapping(target = "remarks", source = "orderRemarks"),
            @Mapping(target = "status", source = "orderStatus"),
    })
    Orders mapDTOtoEntity(OrderDto source);

    @Mappings({
            @Mapping(target = "orderId", source = "id"),
            @Mapping(target = "orderAmount", source = "amount"),
            @Mapping(target = "orderQuantity", source = "quantity"),
            @Mapping(target = "orderRemarks", source = "remarks"),
            @Mapping(target = "orderStatus", source = "status"),
    })
    OrderDto mapEntityToDTO(Orders source);

    List<Orders> mapDTOsToEntities(List<OrderDto> source);

    List<OrderDto> mapEntitiesToDTOs(List<Orders> source);
}
