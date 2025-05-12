package com.yam.backend.model.product;

import com.yam.backend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private long price;

    private String description;

    private String thumbnailUrl;

    @OneToMany
    private List<ProductMedia> mediaList;

    private boolean deleted = false;

    private boolean visible = false;

    private boolean restricted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seller-id", referencedColumnName = "id")
    private User seller;
}
