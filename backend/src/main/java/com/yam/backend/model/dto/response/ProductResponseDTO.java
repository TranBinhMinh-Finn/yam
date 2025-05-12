package com.yam.backend.model.dto.response;

import com.yam.backend.model.product.ProductMedia;
import com.yam.backend.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {
    private UUID id;

    private String name;

    private String thumbnailUrl;

    private long price;

    private String description;

    private List<ProductMedia> mediaList;

    private SellerDTO seller;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SellerDTO {

        private UUID id;

        private String name;

        private String email;
    }

}
