package com.yam.backend.service.admin;

import com.yam.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductService productService;

    public void changeProductRestrictStatus(long id, boolean restrictStatus) {
        productService.changeProductRestrictStatus(id, restrictStatus);
    }
}
