package com.yam.backend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddCartItemDTO {
    @NotBlank
    private UUID productId;

    @NotNull
    @Positive
    private Integer quantity;
}
