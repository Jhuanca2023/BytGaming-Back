package gaming.pe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import  lombok.*;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    private  String lastName;

    @Column(unique = true)
    @JsonIgnore
    private String password;
    private String email;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}