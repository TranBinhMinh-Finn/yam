package com.yam.backend.repository;

import com.yam.backend.model.Product;
import com.yam.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(User seller);
}
