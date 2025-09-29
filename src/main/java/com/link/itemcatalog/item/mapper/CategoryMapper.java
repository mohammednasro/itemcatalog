package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.CategoryCreateRequest;
import com.link.itemcatalog.item.dto.CategoryResponse;
import com.link.itemcatalog.item.dto.CategoryUpdateRequest;
import com.link.itemcatalog.item.model.entity.Category;

/**
 * Mapper for converting category entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryMapper {

    @Mapping(target = "parent", source = "parentId", qualifiedByName = "toCategoryRef")
    Category toEntity(CategoryCreateRequest dto);

    @Mapping(target = "parent", source = "parentId", qualifiedByName = "toCategoryRef")
    void update(@MappingTarget Category entity, CategoryUpdateRequest dto);

    @Mapping(target = "parentId", source = "parent.id")
    CategoryResponse toResponse(Category entity);

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
