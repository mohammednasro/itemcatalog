package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.VariantMediaCreateRequest;
import com.link.itemcatalog.item.dto.VariantMediaResponse;
import com.link.itemcatalog.item.dto.VariantMediaUpdateRequest;
import com.link.itemcatalog.item.model.entity.ItemVariant;
import com.link.itemcatalog.item.model.entity.VariantMedia;

/**
 * Mapper for converting variant media entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VariantMediaMapper {

    @Mapping(target = "variant", source = "variantId", qualifiedByName = "toVariantRef")
    VariantMedia toEntity(VariantMediaCreateRequest dto);

    @Mapping(target = "variant", ignore = true)
    void update(@MappingTarget VariantMedia entity, VariantMediaUpdateRequest dto);

    @Mapping(target = "variantId", source = "variant.id")
    VariantMediaResponse toResponse(VariantMedia entity);

    @Named("toVariantRef")
    default ItemVariant toVariantRef(Long id) {
        if (id == null) {
            return null;
        }
        ItemVariant variant = new ItemVariant();
        variant.setId(id);
        return variant;
    }
}
