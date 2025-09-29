package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.CategoryCreateRequest;
import com.link.itemcatalog.item.dto.CategoryResponse;
import com.link.itemcatalog.item.dto.CategoryUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Facade for managing categories.
 */
public interface CategoryFacade {

    CategoryResponse create(CategoryCreateRequest request);

    CategoryResponse update(Long id, CategoryUpdateRequest request);

    CategoryResponse getById(Long id);

    CategoryResponse getByCode(String code);

    PageResponse<CategoryResponse> list(Pageable pageable);

    void delete(Long id);
}
