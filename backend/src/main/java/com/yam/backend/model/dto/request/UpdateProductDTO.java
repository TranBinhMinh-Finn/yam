package com.yam.backend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {
    @NotBlank
    private String name;

    @NotBlank
    private long price;

    @NotBlank
    private String description;
}
