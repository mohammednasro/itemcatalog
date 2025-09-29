package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.ItemVariantCreateRequest;
import com.link.itemcatalog.item.dto.ItemVariantResponse;
import com.link.itemcatalog.item.dto.ItemVariantUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Facade for managing item variants.
 */
public interface ItemVariantFacade {

    ItemVariantResponse create(ItemVariantCreateRequest request);

    ItemVariantResponse update(Long id, ItemVariantUpdateRequest request);

    ItemVariantResponse getById(Long id);

    ItemVariantResponse getBySku(String sku);

    PageResponse<ItemVariantResponse> list(Pageable pageable);

    void delete(Long id);
}
