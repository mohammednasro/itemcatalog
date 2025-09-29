package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.BrandCreateRequest;
import com.link.itemcatalog.item.dto.BrandResponse;
import com.link.itemcatalog.item.dto.BrandUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Facade for managing brands.
 */
public interface BrandFacade {

    BrandResponse create(BrandCreateRequest request);

    BrandResponse update(Long id, BrandUpdateRequest request);

    BrandResponse getById(Long id);

    BrandResponse getByCode(String code);

    PageResponse<BrandResponse> list(Pageable pageable);

    void delete(Long id);
}
