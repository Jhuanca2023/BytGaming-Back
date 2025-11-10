package gaming.pe.Service.Impl;

import gaming.pe.Entity.Cart;
import gaming.pe.Entity.CartItem;
import gaming.pe.Entity.Product;
import gaming.pe.Repository.CartItemRepository;
import gaming.pe.Repository.CartRepository;
import gaming.pe.Repository.ProductRepository;
import gaming.pe.Service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public CartItem addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .map(existingItem -> {
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                    return cartItemRepository.save(existingItem);
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(quantity);
                    return cartItemRepository.save(newItem);
                });
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .ifPresent(cartItemRepository::delete);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    cartItemRepository.save(item);
                });
    }
}
