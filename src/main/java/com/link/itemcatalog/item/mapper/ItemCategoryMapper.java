package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.ItemCategoryLinkRequest;
import com.link.itemcatalog.item.dto.ItemCategoryResponse;
import com.link.itemcatalog.item.model.entity.Category;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemCategory;

/**
 * Mapper for converting item-category link entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ItemCategoryMapper {

    @Mapping(target = "item", source = "itemId", qualifiedByName = "toItemRef")
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "toCategoryRef")
    ItemCategory toEntity(ItemCategoryLinkRequest dto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "categoryId", source = "category.id")
    ItemCategoryResponse toResponse(ItemCategory entity);

    @Named("toItemRef")
    default Item toItemRef(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }

    @Named("toCategoryRef")
    default Category toCategoryRef(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
