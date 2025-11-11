package gaming.pe.config;



import gaming.pe.Entity.RoleEntity;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Enums.RoleEnum;
import gaming.pe.Repository.RoleRepository;
import gaming.pe.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class CommandInitializerConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar permisos
//        if (permissionRepository.count() == 0) {
//            for (EnumPermission permission : EnumPermission.values()) {
//                PermissionModel newPermission = new PermissionModel();
//                newPermission.setName(permission.name());
//                permissionRepository.save(newPermission);
//            }
//            System.out.println("Permissions initialized.");
//        }

        // Inicializar roles
        if (roleRepository.count() == 0) {
//            Set<PermissionModel> allPermissions = new HashSet<>(permissionRepository.findAll());

            RoleEntity adminRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .build();;
//            adminRole.setPermissions(allPermissions);
            roleRepository.save(adminRole);

                RoleEntity userRole = RoleEntity.builder()
                        .roleEnum(RoleEnum.USER)
                        .build();
            roleRepository.save(userRole);

            System.out.println("Roles initialized.");
        }

        // Crear usuarios iniciales
        if (userRepository.count() == 0) {
            RoleEntity adminRole = roleRepository
                    .findByRoleEnum(RoleEnum.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));

            RoleEntity userRole = roleRepository
                    .findByRoleEnum(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("User role not found"));

            UserEntity admin = UserEntity.builder()
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .name("Roger")
                    .lastName("Concepción")
                    .role(adminRole)
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .build();
            UserEntity admin2 = UserEntity.builder()
                    .email("user1@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(adminRole)
                    .name("Jhon")
                    .lastName("Rodriguez")
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .build();
            UserEntity user2 = UserEntity.builder()
                    .email("user2@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(userRole)
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .build();

            userRepository.save(admin);
            userRepository.save(admin2);
            userRepository.save(user2);

            System.out.println("Initial users created.");
        }
    }
}
