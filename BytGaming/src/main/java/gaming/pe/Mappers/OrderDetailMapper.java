package gaming.pe.Mappers;

import gaming.pe.DTO.OrderDetail.OrderDetailDTO;
import gaming.pe.Entity.OrderDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    List<OrderDetailDTO> toListDto(List<OrderDetail> detail);
}
