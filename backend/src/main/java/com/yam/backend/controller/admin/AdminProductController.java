package com.yam.backend.controller.admin;

import com.yam.backend.model.dto.response.AdminProductDTO;
import com.yam.backend.model.dto.response.PageResponseDTO;
import com.yam.backend.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/products/")
@RequiredArgsConstructor
public class AdminProductController {
    private final AdminProductService adminProductService;

    @PatchMapping("/{id}")
    public ResponseEntity<Void> changeRestrictStatus(@PathVariable UUID id, @RequestParam boolean restrict) {
        adminProductService.changeProductRestrictStatus(id, restrict);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-seller")
    public ResponseEntity<?> getProductByShop(@RequestParam(value="id") UUID id,
                                              @RequestParam(value="page_size") int pageSize,
                                              @RequestParam(value="page_number") int pageNumber) {
        PageResponseDTO<AdminProductDTO> responseDTO = new PageResponseDTO<>(adminProductService
                .getProductsByShop(id, pageSize, pageNumber));
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }
}
