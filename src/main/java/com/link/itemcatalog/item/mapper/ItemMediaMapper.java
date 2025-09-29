package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.ItemMediaCreateRequest;
import com.link.itemcatalog.item.dto.ItemMediaResponse;
import com.link.itemcatalog.item.dto.ItemMediaUpdateRequest;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemMedia;

/**
 * Mapper for converting item media entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ItemMediaMapper {

    @Mapping(target = "item", source = "itemId", qualifiedByName = "toItemRef")
    ItemMedia toEntity(ItemMediaCreateRequest dto);

    @Mapping(target = "item", ignore = true)
    void update(@MappingTarget ItemMedia entity, ItemMediaUpdateRequest dto);

    @Mapping(target = "itemId", source = "item.id")
    ItemMediaResponse toResponse(ItemMedia entity);

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
