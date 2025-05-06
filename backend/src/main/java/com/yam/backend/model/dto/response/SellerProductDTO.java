package com.yam.backend.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SellerProductDTO {
    private UUID id;

    private String name;

    private long price;

    private String description;

    private boolean deleted = false;

    private boolean visible = false;

    private boolean restricted = false;
}
