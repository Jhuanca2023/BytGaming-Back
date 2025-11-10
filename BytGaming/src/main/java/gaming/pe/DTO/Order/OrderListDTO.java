package gaming.pe.DTO.Order;

import gaming.pe.DTO.OrderDetail.OrderDetailDTO;
import gaming.pe.Entity.UserAddress;
import gaming.pe.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
    private Long id;
    private LocalDateTime createdAt;
    private List<OrderDetailDTO> detail;
}
