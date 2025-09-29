package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.ItemCreateRequest;
import com.link.itemcatalog.item.dto.ItemResponse;
import com.link.itemcatalog.item.dto.ItemUpdateRequest;
import com.link.itemcatalog.item.model.entity.Brand;
import com.link.itemcatalog.item.model.entity.Item;

/**
 * Mapper for converting item entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ItemMapper {

    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrandRef")
    @Mapping(target = "variants", ignore = true)
    Item toEntity(ItemCreateRequest dto);

    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrandRef")
    @Mapping(target = "variants", ignore = true)
    void update(@MappingTarget Item entity, ItemUpdateRequest dto);

    @Mapping(target = "brandId", source = "brand.id")
    ItemResponse toResponse(Item entity);

    @Named("toBrandRef")
    default Brand toBrandRef(Long id) {
        if (id == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setId(id);
        return brand;
    }
}
