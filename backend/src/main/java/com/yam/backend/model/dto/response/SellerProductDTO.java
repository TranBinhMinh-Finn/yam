package com.yam.backend.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerProductDTO {
    private long id;

    private String name;

    private long price;

    private String description;

    private boolean deleted = false;

    private boolean visible = false;

    private boolean restricted = false;
}
