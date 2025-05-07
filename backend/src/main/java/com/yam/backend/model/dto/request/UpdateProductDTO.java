package com.yam.backend.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull
    @Min(value = 0, message = "price must not be negative")
    private Long price;

    @NotBlank
    private String description;

    @NotNull
    private Boolean visible;
}
