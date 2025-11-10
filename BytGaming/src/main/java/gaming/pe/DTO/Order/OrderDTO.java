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
public class OrderDTO {
    private Long id;
    private UserEntity user;
    private UserAddress userAddress;
    private LocalDateTime createdAt;
    private List<OrderDetailDTO> detail;
}
