package com.yam.backend.service;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.request.SaveProductDTO;
import com.yam.backend.model.dto.request.UpdateProductDTO;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.ProductRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Transactional
    public Product saveProduct(SaveProductDTO productDTO, User user) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setSeller(user);
        return productRepository.save(product);
    }

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

    @Transactional
    public Product updateProduct(long id, UpdateProductDTO updateProductDTO, User user) {
        Product product = findById(id);
        if(product.getSeller().getId() != user.getId()){
            throw new BadCredentialsException("Product not found");
        }
        product.setName(updateProductDTO.getName());
        product.setPrice(updateProductDTO.getPrice());
        product.setDescription(updateProductDTO.getDescription());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(long id, User user) {
        Product product = findById(id);
        if(product.getSeller().getId() != user.getId()){
            throw new BadCredentialsException("Product not found");
        }
        product.setDeleted(true);
        product.setVisible(false);
        productRepository.save(product);
    }

}
