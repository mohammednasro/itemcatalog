package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.ItemAttributeValueResponse;
import com.link.itemcatalog.item.dto.ItemAttributeValueUpsertRequest;
import com.link.itemcatalog.item.model.entity.AttributeDefinition;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemAttributeValue;

/**
 * Mapper for converting item attribute value entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ItemAttributeValueMapper {

    @Mapping(target = "item", source = "itemId", qualifiedByName = "toItemRef")
    @Mapping(target = "attribute", source = "attributeId", qualifiedByName = "toAttributeRef")
    ItemAttributeValue toEntity(ItemAttributeValueUpsertRequest dto);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "attribute", ignore = true)
    void update(@MappingTarget ItemAttributeValue entity, ItemAttributeValueUpsertRequest dto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "attributeId", source = "attribute.id")
    ItemAttributeValueResponse toResponse(ItemAttributeValue entity);

    @Named("toItemRef")
    default Item toItemRef(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }

    @Named("toAttributeRef")
    default AttributeDefinition toAttributeRef(Long id) {
        if (id == null) {
            return null;
        }
        AttributeDefinition attribute = new AttributeDefinition();
        attribute.setId(id);
        return attribute;
    }
}
