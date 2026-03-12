package it.unical.inf.ae.core.mapper;

import it.unical.inf.ae.shared.dto.OrderDto;
import it.unical.inf.ae.shared.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userFullName", expression = "java(order.getUser().getFirstName() + \" \" + order.getUser().getLastName())")
    OrderDto toDto(Order order);
    
    List<OrderDto> toDtoList(List<Order> orders);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDto orderDto);
}