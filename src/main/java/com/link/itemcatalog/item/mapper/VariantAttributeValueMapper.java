package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.VariantAttributeValueResponse;
import com.link.itemcatalog.item.dto.VariantAttributeValueUpsertRequest;
import com.link.itemcatalog.item.model.entity.AttributeDefinition;
import com.link.itemcatalog.item.model.entity.ItemVariant;
import com.link.itemcatalog.item.model.entity.VariantAttributeValue;

/**
 * Mapper for converting variant attribute value entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VariantAttributeValueMapper {

    @Mapping(target = "variant", source = "variantId", qualifiedByName = "toVariantRef")
    @Mapping(target = "attribute", source = "attributeId", qualifiedByName = "toAttributeRef")
    VariantAttributeValue toEntity(VariantAttributeValueUpsertRequest dto);

    @Mapping(target = "variant", ignore = true)
    @Mapping(target = "attribute", ignore = true)
    void update(@MappingTarget VariantAttributeValue entity, VariantAttributeValueUpsertRequest dto);

    @Mapping(target = "variantId", source = "variant.id")
    @Mapping(target = "attributeId", source = "attribute.id")
    VariantAttributeValueResponse toResponse(VariantAttributeValue entity);

    @Named("toVariantRef")
    default ItemVariant toVariantRef(Long id) {
        if (id == null) {
            return null;
        }
        ItemVariant variant = new ItemVariant();
        variant.setId(id);
        return variant;
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
