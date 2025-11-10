package gaming.pe.Repository;

import gaming.pe.Entity.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KardexRepository  extends JpaRepository<Kardex,Long> {
}
