package com.yam.backend.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
public class UpdateProductDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull
    @Min(value = 0, message = "Price must not be negative")
    private Long price;

    @NotBlank
    @URL
    private String thumbnailUrl;

    @NotBlank
    private String description;

    @NotNull
    private Boolean visible;

    @Valid
    @NotEmpty
    private List<ProductMediaDTO> mediaList;
}
