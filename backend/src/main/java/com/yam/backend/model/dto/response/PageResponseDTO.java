package com.yam.backend.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponseDTO<T> {

    private List<T> content;

    private int totalElements;

    private int totalPages;

    private int pageSize;

    private int pageNumber;

    private int numberOfElements;

    public PageResponseDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
    }

}
