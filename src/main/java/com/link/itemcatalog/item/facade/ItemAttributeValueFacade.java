package com.link.itemcatalog.item.facade;

import com.link.itemcatalog.item.dto.ItemAttributeValueResponse;
import com.link.itemcatalog.item.dto.ItemAttributeValueUpsertRequest;

/**
 * Facade for managing item attribute values.
 */
public interface ItemAttributeValueFacade {

    ItemAttributeValueResponse upsert(ItemAttributeValueUpsertRequest request);

    void delete(Long id);
}
