package gaming.pe.DTO.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCardRegisterDTO {
    private long cartId;
    private long userAddressId;
}
