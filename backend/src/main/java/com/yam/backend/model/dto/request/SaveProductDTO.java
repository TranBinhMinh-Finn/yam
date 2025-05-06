package com.yam.backend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveProductDTO {
    @NotBlank
    private String name;

    @NotNull
    private Long price;

    @NotBlank
    private String description;

    @NotNull
    private Boolean visible;
}
