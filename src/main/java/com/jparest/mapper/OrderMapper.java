package com.jparest.mapper;

import com.jparest.model.Orders;
import com.jparest.model.dto.OrderDto;
import com.jparest.model.dto.OrderItemDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ItemMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", source = "orderId"),
            @Mapping(target = "amount", source = "orderAmount"),
            @Mapping(target = "quantity", source = "orderQuantity"),
            @Mapping(target = "remarks", source = "orderRemarks"),
            @Mapping(target = "status", source = "orderStatus"),
    })
    Orders mapToOrderEntity(OrderDto source);

    @IterableMapping(elementTargetType = Orders.class)
    List<Orders> mapToOrderEntityList(List<OrderDto> source);

    @Mappings({
            @Mapping(target = "orderId", source = "id"),
            @Mapping(target = "orderAmount", source = "amount"),
            @Mapping(target = "orderQuantity", source = "quantity"),
            @Mapping(target = "orderRemarks", source = "remarks"),
            @Mapping(target = "orderStatus", source = "status"),
    })
    OrderDto mapToOrderDto(Orders source);

    @IterableMapping(elementTargetType = OrderDto.class)
    List<OrderDto> mapToOrderDtoList(List<Orders> source);

    @Mappings({
            @Mapping(target = "orderId", source = "id"),
            @Mapping(target = "orderAmount", source = "amount"),
            @Mapping(target = "orderQuantity", source = "quantity"),
            @Mapping(target = "orderRemarks", source = "remarks"),
            @Mapping(target = "orderStatus", source = "status"),
    })
    OrderItemDto mapToOrderItemDto(Orders source);

    @IterableMapping(elementTargetType = OrderItemDto.class)
    List<OrderItemDto> mapToOrderItemDtoList(List<Orders> source);
}
