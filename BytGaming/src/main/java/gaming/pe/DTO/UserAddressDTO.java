package gaming.pe.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dirrection;
    private LocalDateTime createdAt;
}