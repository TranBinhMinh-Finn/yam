package com.yam.backend.model.dto.response;

import com.yam.backend.model.product.ProductMedia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SellerProductDTO {
    private UUID id;

    private String name;

    private long price;

    private String thumbnailUrl;

    private String description;

    private List<ProductMedia> mediaList;

    private boolean deleted = false;

    private boolean visible = false;

    private boolean restricted = false;
}
