package com.yam.backend.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
public class SaveProductDTO {
    @NotBlank
    private String name;

    @NotNull
    private Long price;

    @NotBlank
    @URL
    private String thumbnailUrl;

    @NotBlank
    private String description;

    @NotNull
    private Boolean visible;

    @NotEmpty
    @Valid
    private List<ProductMediaDTO> mediaList;
}
