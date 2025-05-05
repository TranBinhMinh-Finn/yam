package com.yam.backend.service.admin;

import com.yam.backend.model.Product;
import com.yam.backend.model.dto.response.AdminProductDTO;
import com.yam.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    public void changeProductRestrictStatus(long id, boolean restrictStatus) {
        productService.changeProductRestrictStatus(id, restrictStatus);
    }

    public Page<AdminProductDTO> getProductsByShop(long shop_id,
                                           int pageSize,
                                           int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> res = productService.getProductsBySellerForAdmin(shop_id, pageable);
        return res.map(product -> modelMapper.map(product, AdminProductDTO.class));
    }
}
