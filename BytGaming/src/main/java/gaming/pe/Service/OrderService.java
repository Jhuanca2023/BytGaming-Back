package gaming.pe.Service;

import gaming.pe.DTO.Order.OrderCardRegisterDTO;
import gaming.pe.DTO.Order.OrderDTO;
import gaming.pe.DTO.Order.OrderListDTO;
import gaming.pe.Entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(OrderCardRegisterDTO request, String email);
    boolean delete(Long id);
}
