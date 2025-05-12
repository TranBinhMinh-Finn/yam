package com.yam.backend.controller;

import com.yam.backend.model.product.Product;
import com.yam.backend.model.dto.response.ProductResponseDTO;
import com.yam.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID id) {
        Product product = productService.findPubliclyVisibleProductById(id);
        ProductResponseDTO responseDTO = modelMapper.map(product, ProductResponseDTO.class);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }
}
