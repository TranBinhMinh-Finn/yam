package com.yam.backend.model;

import com.yam.backend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private long price;

    private String description;

    private boolean deleted = false;

    private boolean visible = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seller-id", referencedColumnName = "id")
    private User seller;
}
