package com.link.itemcatalog.item.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.link.itemcatalog.item.dto.ItemVariantCreateRequest;
import com.link.itemcatalog.item.dto.ItemVariantResponse;
import com.link.itemcatalog.item.dto.ItemVariantUpdateRequest;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemVariant;

/**
 * Mapper for converting item variant entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = DimensionsMapper.class)
public interface ItemVariantMapper {

    @Mapping(target = "item", source = "itemId", qualifiedByName = "toItemRef")
    @Mapping(target = "isDefault", expression = "java(Boolean.TRUE.equals(dto.getIsDefault()))")
    ItemVariant toEntity(ItemVariantCreateRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "isDefault", expression = "java(dto.getIsDefault() != null ? dto.getIsDefault() : entity.isDefault())")
    void update(@MappingTarget ItemVariant entity, ItemVariantUpdateRequest dto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "isDefault", expression = "java(entity.isDefault())")
    ItemVariantResponse toResponse(ItemVariant entity);

    @Named("toItemRef")
    default Item toItemRef(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
