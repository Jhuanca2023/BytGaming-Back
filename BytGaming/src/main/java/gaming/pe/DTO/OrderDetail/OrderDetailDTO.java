package gaming.pe.DTO.OrderDetail;

import gaming.pe.Entity.Order;
import gaming.pe.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double unit_price;
}
