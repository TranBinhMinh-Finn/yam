package com.yam.backend.service;

import com.yam.backend.exception.RequestException;
import com.yam.backend.model.product.Product;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RequestException("Product not found"));
    }

    public Product findPubliclyVisibleProductById(UUID id) {
        return productRepository.findByIdAndVisibleAndDeletedAndRestricted(id, true, false, false)
                .orElseThrow(() -> new RequestException("Product not found"));
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

    public void deleteProduct(UUID id) {
        Product product = findById(id);
        product.setDeleted(true);
        product.setVisible(false);
        productRepository.save(product);
    }

    public void changeProductRestrictStatus(UUID id, boolean status) {
        Product product = findById(id);
        product.setRestricted(status);
        productRepository.save(product);
    }

    public List<Product> getAllBySeller(UUID sellerId) {
        User user = userService.findById(sellerId);
        return productRepository.findAllBySeller(user);
    }

    public Page<Product> getProductListForSeller(User seller, Pageable pageable) {
        return productRepository.findAllBySellerAndDeleted(seller, false, pageable);
    }

    public Page<Product> getProductsBySellerForAdmin(UUID sellerId, Pageable pageable) {
        User user = userService.findById(sellerId);
        return productRepository.findAllBySeller(user, pageable);
    }

}
