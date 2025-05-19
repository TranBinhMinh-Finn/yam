package com.yam.backend.controller;

import com.yam.backend.model.cart.Cart;
import com.yam.backend.model.dto.request.AddCartItemDTO;
import com.yam.backend.model.dto.request.EditCartItemDTO;
import com.yam.backend.model.dto.response.CartDTO;
import com.yam.backend.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getCart() {
        Cart res = cartService.getCurrentUserCart();
        CartDTO cartDTO = modelMapper.map(res, CartDTO.class);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody @Valid AddCartItemDTO addCartItemDTO) {
        Cart res = cartService.addToCart(addCartItemDTO.getProductId(), addCartItemDTO.getQuantity());
        CartDTO cartDTO = modelMapper.map(res, CartDTO.class);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping
    public ResponseEntity<?> removeItem(@RequestParam UUID itemId) {
        cartService.removeFromCart(itemId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> editItem(@RequestBody @Valid EditCartItemDTO editCartItemDTO) {
        cartService.editCartItem(editCartItemDTO.getItemId(), editCartItemDTO.getQuantity());
        return ResponseEntity.ok().build();
    }
}
