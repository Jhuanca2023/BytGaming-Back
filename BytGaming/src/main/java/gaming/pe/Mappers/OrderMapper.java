package gaming.pe.Mappers;

import gaming.pe.DTO.Order.OrderDTO;
import gaming.pe.DTO.Order.OrderListDTO;
import gaming.pe.Entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    List<OrderListDTO> toListDto(List<Order> order);
    OrderDTO toLDto(Order order);
}
