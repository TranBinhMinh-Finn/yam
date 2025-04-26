package com.yam.backend.controller;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.request.SaveProductDTO;
import com.yam.backend.model.dto.request.UpdateProductDTO;
import com.yam.backend.model.dto.response.ProductResponseDTO;
import com.yam.backend.model.user.User;
import com.yam.backend.service.ProductService;
import com.yam.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> listProduct() {
        return ResponseEntity
                .ok()
                .body(productService.listProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable long id) {
        ProductResponseDTO responseDTO = new ProductResponseDTO(productService.findById(id));
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    @GetMapping("/seller/{sellerId}/all")
    public ResponseEntity<List<Product>> listProductBySeller(@PathVariable long sellerId) {
        return ResponseEntity
                .ok()
                .body(productService.listProductBySeller(sellerId));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid SaveProductDTO productDTO,
                                              Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        User user = userService.findByEmail(email);

        Product product = productService.saveProduct(productDTO, user);
        ProductResponseDTO responseDTO = new ProductResponseDTO(product);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable long id,
                                                 @Valid @RequestBody UpdateProductDTO productDTO,
                                                 Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        User user = userService.findByEmail(email);

        Product product = productService.updateProduct(id, productDTO, user);
        ProductResponseDTO responseDTO = new ProductResponseDTO(product);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id,
                                              Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        User user = userService.findByEmail(email);

        productService.deleteProduct(id, user);
        return ResponseEntity.noContent().build();
    }

}
