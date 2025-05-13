package com.yam.backend.repository;

import com.yam.backend.model.cart.Cart;
import com.yam.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
