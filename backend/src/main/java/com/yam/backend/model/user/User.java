package com.yam.backend.model.user;

import com.yam.backend.model.cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



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
    private long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String role = Role.CUSTOMER.toString();

    @OneToOne
    private Cart cart;
}
