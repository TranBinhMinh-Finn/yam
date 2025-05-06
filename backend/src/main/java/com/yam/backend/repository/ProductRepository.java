package com.yam.backend.repository;

import com.yam.backend.model.Product;
import com.yam.backend.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBySeller(User seller);

    Page<Product> findAllBySeller(User seller, Pageable pageable);

    Page<Product> findAllBySellerAndDeleted(User seller, boolean deleted, Pageable pageable);
}
