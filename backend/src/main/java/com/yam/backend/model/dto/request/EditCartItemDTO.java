package com.yam.backend.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EditCartItemDTO {
    @NotNull
    UUID itemId;

    @NotNull
    @Min(1)
    Integer quantity;
}
