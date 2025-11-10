package gaming.pe.Controller;

import gaming.pe.Entity.Cart;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Repository.UsersRepository;
import gaming.pe.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UsersRepository userRepository;

    // Crear carrito para el usuario autenticado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCartForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Obtiene el email del usuario autenticado

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return cartService.createCartForUser(user.getId());
    }

    // Agregar un producto al carrito del usuario autenticado
    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCart(@PathVariable Long productId, @RequestParam int quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Obtiene el email del usuario autenticado

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        cartService.addProductToCart(user.getId(), productId, quantity);
    }

    // ❌ Eliminar producto del carrito
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        cartService.removeProductFromCart(user.getId(), productId);
        return ResponseEntity.ok("Producto eliminado del carrito");
    }
    // 🔍 Obtener carrito del usuario
    @GetMapping
    public ResponseEntity<Cart> getCartForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Cart cart = cartService.getOrCreateCart(user.getId());
        return ResponseEntity.ok(cart);
    }

    // 🗑️ Vaciar carrito
    @DeleteMapping
    public ResponseEntity<String> clearCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        cartService.clearCart(user.getId());
        return ResponseEntity.ok("Carrito vaciado");
    }

}
