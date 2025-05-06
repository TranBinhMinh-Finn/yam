package com.yam.backend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AddCartItemDTO {
    @NotBlank
    private Long productId;

    @NotBlank
    @Positive
    private Integer quantity;
}
