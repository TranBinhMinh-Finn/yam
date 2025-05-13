package com.yam.backend.model.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartDTO {

    List<CartItemDTO> items;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartItemDTO {
        private UUID id;

        private ProductResponseDTO product;

        private int quantity;
    }
}
