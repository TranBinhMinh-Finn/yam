package com.yam.backend.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminProductDTO {
    private long id;

    private String name;

    private long price;

    private String description;

    private boolean deleted;

    private boolean visible;

    private boolean restricted;
}
