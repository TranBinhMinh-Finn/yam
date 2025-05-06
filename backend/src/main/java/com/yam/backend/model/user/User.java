package com.yam.backend.model.user;

import com.yam.backend.model.cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
public class User {
    public enum Role {
        ADMIN,
        SELLER,
        CUSTOMER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String role = Role.CUSTOMER.toString();

    @OneToOne
    private Cart cart;

    private boolean deleted = false;

    private boolean active = true;
}
