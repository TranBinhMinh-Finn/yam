package com.yam.backend.service;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.request.SaveProductDTO;
import com.yam.backend.model.dto.request.UpdateProductDTO;
import com.yam.backend.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final ProductService productService;
    private final UserService userService;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        return userService.findByEmail(email);
    }

    @Transactional
    public Product saveProduct(SaveProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setSeller(getCurrentUser());
        product.setVisible(productDTO.isVisible());

        return productService.saveProduct(product);
    }

    @Transactional
    public Product updateProduct(long id, UpdateProductDTO updateProductDTO) {
        Product product = productService.findById(id);
        if(product.getSeller().getId() != getCurrentUser().getId()){
            throw new RuntimeException("Product not found");
        }
        product.setName(updateProductDTO.getName());
        product.setPrice(updateProductDTO.getPrice());
        product.setDescription(updateProductDTO.getDescription());
        product.setVisible(updateProductDTO.isVisible());

        return productService.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(long id) {
        Product product = productService.findById(id);
        if(product.getSeller().getId() != getCurrentUser().getId()){
            throw new RuntimeException("Product not found");
        }
        productService.deleteProduct(id);
    }


}
