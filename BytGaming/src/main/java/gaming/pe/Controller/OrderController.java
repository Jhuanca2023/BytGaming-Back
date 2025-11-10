package gaming.pe.Controller;

import gaming.pe.DTO.Order.OrderCardRegisterDTO;
import gaming.pe.DTO.Order.OrderDTO;
import gaming.pe.DTO.Order.OrderListDTO;
import gaming.pe.Entity.Order;
import gaming.pe.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/test")
    public ResponseEntity<Boolean> test() {
        return ResponseEntity.ok( true);
    }


    @GetMapping
    public ResponseEntity<List<Order>> get() {
        try {
            var response  = service.findAll();
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable long id) {
        var response  = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cart")
    public ResponseEntity<Order> add(Authentication authentication, @RequestBody OrderCardRegisterDTO request) {
        String email = authentication.getName();
        var response = service.save(request, email);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) {
        var response  = service.delete(id);
        return ResponseEntity.ok(response);
    }
}
