package com.yam.backend.controller.admin;

import com.yam.backend.model.user.User;
import com.yam.backend.service.ProductService;
import com.yam.backend.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products/")
@RequiredArgsConstructor
public class AdminProductController {
    private final AdminProductService adminProductService;

    @PutMapping("/{id}")
    public ResponseEntity<Void> changeRestrictStatus(@PathVariable int id, @RequestParam boolean restrict) {
        adminProductService.changeProductRestrictStatus(id, restrict);
        return ResponseEntity.ok().build();
    }

    
}
