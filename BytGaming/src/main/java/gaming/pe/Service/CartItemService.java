package gaming.pe.Service;

import gaming.pe.Entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> getCartItems(Long cartId);
    CartItem addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
}
