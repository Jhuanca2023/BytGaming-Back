package gaming.pe.Repository;

import gaming.pe.Entity.RoleEntity;
import gaming.pe.Enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findFirstByRoleEnumIn(List<String> roles);
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
