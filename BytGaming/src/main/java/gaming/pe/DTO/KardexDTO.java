package gaming.pe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KardexDTO {
    private Long productId;
    private Integer quantity;
    private String movementType; // o MovementType
    private LocalDateTime date;
    private BigDecimal cost;
    private Integer balance;
    private Long supplierId;
    private Long staffId;

}