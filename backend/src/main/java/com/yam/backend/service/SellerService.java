package com.yam.backend.service;

import com.yam.backend.exception.RequestException;
import com.yam.backend.model.Product;
import com.yam.backend.model.dto.request.SaveProductDTO;
import com.yam.backend.model.dto.request.UpdateProductDTO;
import com.yam.backend.model.dto.response.SellerProductDTO;
import com.yam.backend.model.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final ModelMapper modelMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        return userService.findByEmail(email);
    }

    public Page<SellerProductDTO> getSellerProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> res = productService.getProductListForSeller(getCurrentUser(), pageable);
        return res.map(product -> modelMapper.map(product, SellerProductDTO.class));
    }

    @Transactional
    public Product saveProduct(SaveProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setSeller(getCurrentUser());

        return productService.saveProduct(product);
    }

    @Transactional
    public Product updateProduct(long id, UpdateProductDTO updateProductDTO) {
        Product product = productService.findById(id);
        if (product.getSeller().getId() != getCurrentUser().getId()) {
            throw new RequestException("Product not found");
        }
        Product updatedProduct = modelMapper.map(updateProductDTO, Product.class);
        updatedProduct.setId(product.getId());
        updatedProduct.setSeller(product.getSeller());
        updatedProduct.setDeleted(product.isDeleted());
        updatedProduct.setRestricted(product.isRestricted());

        return productService.updateProduct(updatedProduct);
    }

    @Transactional
    public void deleteProduct(long id) {
        Product product = productService.findById(id);
        if (product.getSeller().getId() != getCurrentUser().getId()) {
            throw new RequestException("Product not found");
        }
        productService.deleteProduct(id);
    }
}
