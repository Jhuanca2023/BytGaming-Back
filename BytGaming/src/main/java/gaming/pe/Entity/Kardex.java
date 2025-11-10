package gaming.pe.Entity;

import gaming.pe.Enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "kardex")
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private LocalDateTime date;

    private BigDecimal cost;

    private Integer balance;

    @ManyToOne
    @JoinColumn(name = "Supplier_id", referencedColumnName = "id")
    private Supplier provider;  // Relación con el proveedor

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;  // Relación con el empleado (staff)

}
