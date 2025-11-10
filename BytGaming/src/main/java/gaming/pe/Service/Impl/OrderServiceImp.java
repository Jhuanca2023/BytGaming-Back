package gaming.pe.Service.Impl;

import gaming.pe.DTO.Order.OrderCardRegisterDTO;
import gaming.pe.DTO.Order.OrderDTO;
import gaming.pe.DTO.Order.OrderListDTO;
import gaming.pe.Entity.Order;
import gaming.pe.Entity.OrderDetail;
import gaming.pe.Mappers.OrderDetailMapper;
import gaming.pe.Mappers.OrderMapper;
import gaming.pe.Repository.*;
import gaming.pe.Service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository repository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository    cartRepository;
    private final UsersRepository usersRepository;
    private final UserAddressRepository userAddressRepository;

    @Override
    public List<Order> findAll() {
       var orders = repository.findAll();

        orders.forEach(order -> {
            var details = orderDetailRepository.findByOrderId(order.getId());
            order.setDetail(details);
        });

       return repository.findAll();
    }

    @Override
    public Order findById(Long id) {
//        var order = mapper.toLDto(repository.findById(id).orElseThrow());
//        var details = orderDetailRepository.findByOrderId(id);
//        order.setDetail(detailMapper.toListDto(details));
        return repository.findById(id).orElseThrow();

    }

    @Override
    @Transactional
    public Order save(OrderCardRegisterDTO request, String email) {
        var cart = cartRepository.findById(request.getCartId()).orElseThrow();
        var user = usersRepository.findUserEntitiesByEmail(email).orElseThrow();
        var address = userAddressRepository.findById(request.getUserAddressId()).orElseThrow();
        var order = new Order();
        order.setUser(user);
        order.setUserAddress(address);
        order.setCreatedAt(LocalDateTime.now());

        order = repository.save(order);

        for (var item : cart.getItems()) {
            var detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setUnit_price(item.getProduct().getPrice());
            orderDetailRepository.save(detail);
        }

        cartRepository.delete(cart);

        return order;
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
