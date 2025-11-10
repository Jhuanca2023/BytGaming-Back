package gaming.pe.Entity;

import jakarta.persistence.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod; // Método de pago (por ejemplo, tarjeta, efectivo, etc.)

    private Double amount; // Monto del pago

    // Relación Many-to-One con Order (un pago pertenece a una sola orden)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // La orden asociada con este pago

}
