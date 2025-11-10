package gaming.pe.Service.Impl;

import gaming.pe.Entity.Cart;
import gaming.pe.Entity.CartItem;
import gaming.pe.Entity.Product;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Repository.CartItemRepository;
import gaming.pe.Repository.CartRepository;
import gaming.pe.Repository.ProductRepository;
import gaming.pe.Repository.UsersRepository;
import gaming.pe.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository;

    @Override
    public Cart createCartForUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    @Override
    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> createCartForUser(userId));
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

}
