package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.ItemCategoryLinkRequest;
import com.link.itemcatalog.item.dto.ItemCategoryResponse;
import com.link.itemcatalog.item.dto.ItemCreateRequest;
import com.link.itemcatalog.item.dto.ItemResponse;
import com.link.itemcatalog.item.dto.ItemUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Facade for managing item masters.
 */
public interface ItemFacade {

    ItemResponse create(ItemCreateRequest request);

    ItemResponse update(Long id, ItemUpdateRequest request);

    ItemResponse getById(Long id);

    ItemResponse getByCode(String code);

    PageResponse<ItemResponse> list(Pageable pageable);

    void delete(Long id);

    ItemCategoryResponse linkCategory(ItemCategoryLinkRequest request);

    void unlinkCategory(Long itemCategoryId);
}
