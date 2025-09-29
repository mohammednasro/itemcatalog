package com.link.itemcatalog.item.facade;

import com.link.itemcatalog.item.dto.VariantAttributeValueResponse;
import com.link.itemcatalog.item.dto.VariantAttributeValueUpsertRequest;

/**
 * Facade for managing variant attribute values.
 */
public interface VariantAttributeValueFacade {

    VariantAttributeValueResponse upsert(VariantAttributeValueUpsertRequest request);

    void delete(Long id);
}
