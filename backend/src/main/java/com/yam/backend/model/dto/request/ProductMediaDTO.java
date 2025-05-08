package com.yam.backend.model.dto.request;

import com.yam.backend.service.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Getter
@Setter
public class ProductMediaDTO {
    private UUID id;

    @NotBlank
    @URL
    private String url;

    @NotNull
    private MediaType mediaType;
}
