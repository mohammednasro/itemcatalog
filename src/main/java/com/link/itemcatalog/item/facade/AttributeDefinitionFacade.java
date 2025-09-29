package com.link.itemcatalog.item.facade;

import org.springframework.data.domain.Pageable;

import com.link.itemcatalog.item.dto.AttributeDefinitionCreateRequest;
import com.link.itemcatalog.item.dto.AttributeDefinitionResponse;
import com.link.itemcatalog.item.dto.AttributeDefinitionUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Facade for managing attribute definitions.
 */
public interface AttributeDefinitionFacade {

    AttributeDefinitionResponse create(AttributeDefinitionCreateRequest request);

    AttributeDefinitionResponse update(Long id, AttributeDefinitionUpdateRequest request);

    AttributeDefinitionResponse getById(Long id);

    AttributeDefinitionResponse getByCode(String code);

    PageResponse<AttributeDefinitionResponse> list(Pageable pageable);

    void delete(Long id);
}
