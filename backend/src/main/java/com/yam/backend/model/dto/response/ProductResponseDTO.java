package com.yam.backend.model.dto.response;

import com.yam.backend.model.Product;
import com.yam.backend.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.seller = new SellerDTO(product.getSeller());
    }

    private long id;

    private String name;

    private long price;

    private String description;

    private SellerDTO seller;

    @Getter
    @Setter
    public static class SellerDTO {

        public SellerDTO(User seller) {
            this.email = seller.getEmail();
            this.name = seller.getName();
            this.id = seller.getId();
        }

        private long id;

        private String name;

        private String email;
    }

}
