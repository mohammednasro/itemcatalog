package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.ItemMediaCreateRequest;
import com.link.itemcatalog.item.dto.ItemMediaResponse;
import com.link.itemcatalog.item.dto.ItemMediaUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Facade for managing item media.
 */
public interface ItemMediaFacade {

    ItemMediaResponse create(ItemMediaCreateRequest request);

    ItemMediaResponse update(Long id, ItemMediaUpdateRequest request);

    ItemMediaResponse getById(Long id);

    PageResponse<ItemMediaResponse> list(Pageable pageable);

    void delete(Long id);
}
