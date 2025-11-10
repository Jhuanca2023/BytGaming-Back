package gaming.pe.Service;

import gaming.pe.Entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    Optional<OrderDetail> findById(Long id);
    List<OrderDetail> findAll();
    List<OrderDetail> findByOrderId(Long orderId);
    List<OrderDetail> findByProductId(Long productId);
    OrderDetail create(OrderDetail orderDetail);
    OrderDetail update(Long id, OrderDetail orderDetail);
    void delete(Long id);
}
