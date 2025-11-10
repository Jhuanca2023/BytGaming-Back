package gaming.pe.Service;

import gaming.pe.Entity.Cart;

public interface CartService {
    Cart createCartForUser(Long userId);
    void addProductToCart(Long userId, Long productId, int quantity);
    void removeProductFromCart(Long userId, Long productId);
    Cart getOrCreateCart(Long userId);
    void clearCart(Long userId);
}
