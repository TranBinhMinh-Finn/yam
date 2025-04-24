package com.yam.backend.model.cart;

import com.yam.backend.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItem> items;
}
