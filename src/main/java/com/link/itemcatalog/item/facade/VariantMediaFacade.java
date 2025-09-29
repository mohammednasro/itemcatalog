package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.dto.VariantMediaCreateRequest;
import com.link.itemcatalog.item.dto.VariantMediaResponse;
import com.link.itemcatalog.item.dto.VariantMediaUpdateRequest;

/**
 * Facade for managing variant media.
 */
public interface VariantMediaFacade {

    VariantMediaResponse create(VariantMediaCreateRequest request);

    VariantMediaResponse update(Long id, VariantMediaUpdateRequest request);

    VariantMediaResponse getById(Long id);

    PageResponse<VariantMediaResponse> list(Pageable pageable);

    void delete(Long id);
}
