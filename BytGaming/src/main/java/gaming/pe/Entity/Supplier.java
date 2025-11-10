package gaming.pe.Entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;


    @Entity
    @Table(name = "suppliers")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Supplier{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

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
