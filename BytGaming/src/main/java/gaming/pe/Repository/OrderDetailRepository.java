package gaming.pe.Repository;

import gaming.pe.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId); // Obtener todos los detalles por ID de orden
    List<OrderDetail> findByProductId(Long productId);
}
