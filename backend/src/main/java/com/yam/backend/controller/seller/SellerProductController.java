package com.yam.backend.controller.seller;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.request.SaveProductDTO;
import com.yam.backend.model.dto.request.UpdateProductDTO;
import com.yam.backend.model.dto.response.ProductResponseDTO;
import com.yam.backend.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/seller/")
@RequiredArgsConstructor
public class SellerProductController {
    SellerService sellerService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid SaveProductDTO productDTO) {
        Product product = sellerService.saveProduct(productDTO);
        ProductResponseDTO responseDTO = new ProductResponseDTO(product);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable long id,
                                                            @Valid @RequestBody UpdateProductDTO productDTO) {
        Product product = sellerService.updateProduct(id, productDTO);
        ProductResponseDTO responseDTO = new ProductResponseDTO(product);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        sellerService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
