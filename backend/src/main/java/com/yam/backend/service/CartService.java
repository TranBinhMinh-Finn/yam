package com.yam.backend.service;

import com.yam.backend.exception.RequestException;
import com.yam.backend.model.cart.Cart;
import com.yam.backend.model.cart.CartItem;
import com.yam.backend.model.product.Product;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.CartItemRepository;
import com.yam.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final UserService userService;

    private final ProductService productService;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        return userService.findByEmail(email);
    }

    public Cart getCurrentUserCart() {
        return findByUser(getCurrentUser());
    }

    public Cart findByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if(cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    public Cart addToCart(UUID productId, Integer quantity) {
        Cart cart = getCurrentUserCart();
        Product product = productService.findById(productId);

        if(product.getQuantity() < quantity) {
            throw new RequestException("Quantity exceeds product stock");
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public void removeFromCart(UUID cartId) {
        Cart cart = getCurrentUserCart();
        CartItem item = cartItemRepository.getCartItemById(cartId);
        cart.getItems().remove(item);
        cartRepository.save(cart);
        cartItemRepository.delete(item);
    }
}
