package com.yam.backend.service;

import com.yam.backend.model.Product;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> listProductBySeller(long sellerId) {
        User user = userService.findById(sellerId);
        return productRepository.findBySeller(user);
    }

    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        Product product = findById(id);
        product.setDeleted(true);
        product.setVisible(false);
        productRepository.save(product);
    }

    public void changeProductRestrictStatus(long id, boolean status) {
        Product product = findById(id);
        product.setRestricted(status);
        productRepository.save(product);
    }
}
