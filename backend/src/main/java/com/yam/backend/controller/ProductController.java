package com.yam.backend.controller;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.response.ProductResponseDTO;
import com.yam.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

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
}
