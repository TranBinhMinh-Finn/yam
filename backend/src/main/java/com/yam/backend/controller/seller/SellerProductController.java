package com.yam.backend.controller.seller;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.request.SaveProductDTO;
import com.yam.backend.model.dto.request.UpdateProductDTO;
import com.yam.backend.model.dto.response.PageResponseDTO;
import com.yam.backend.model.dto.response.SellerProductDTO;
import com.yam.backend.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/seller/products")
@RequiredArgsConstructor
public class SellerProductController {
    private final SellerService sellerService;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getAllSellerProducts(@RequestParam(value="page_number") int pageNumber,
                                                                       @RequestParam(value="page_size") int pageSize ) {
        Page<SellerProductDTO> res = sellerService.getSellerProducts(pageNumber, pageSize);
        PageResponseDTO<SellerProductDTO> pageResponseDTO = new PageResponseDTO<>(res);
        return ResponseEntity.ok(pageResponseDTO);
    }

    @PostMapping
    public ResponseEntity<SellerProductDTO> addProduct(@RequestBody @Valid SaveProductDTO productDTO) {
        Product product = sellerService.saveProduct(productDTO);
        SellerProductDTO responseDTO = modelMapper.map(product, SellerProductDTO.class);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SellerProductDTO> updateProduct(@PathVariable long id,
                                                          @Valid @RequestBody UpdateProductDTO productDTO) {
        Product product = sellerService.updateProduct(id, productDTO);
        SellerProductDTO responseDTO = modelMapper.map(product, SellerProductDTO.class);
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
