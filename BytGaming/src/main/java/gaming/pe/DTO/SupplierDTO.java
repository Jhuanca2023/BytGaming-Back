package gaming.pe.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {
    private String name;

    @Column(unique = true)
    private String ruc;

    @Column(unique = true)
    @Email
    private String email;

    @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener 9 dígitos")
    @Column(unique = true)
    private String phone;

    private Boolean isActive;
}
