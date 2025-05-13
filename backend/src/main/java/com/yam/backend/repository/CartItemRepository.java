package com.yam.backend.repository;

import com.yam.backend.model.cart.Cart;
import com.yam.backend.model.cart.CartItem;
import com.yam.backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    CartItem getCartItemByCartAndProduct(Cart cart, Product product);

    CartItem getCartItemById(UUID id);

    UUID cart(Cart cart);
}
