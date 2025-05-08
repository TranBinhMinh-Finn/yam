package com.yam.backend.service;

import com.yam.backend.model.product.ProductMedia;
import com.yam.backend.repository.ProductMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMediaService {
    private final ProductMediaRepository productMediaRepository;

    public List<ProductMedia> saveAll(List<ProductMedia> productMediaList) {
        return productMediaRepository.saveAll(productMediaList);
    }
}
