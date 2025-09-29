package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.BrandCreateRequest;
import com.link.itemcatalog.item.dto.BrandResponse;
import com.link.itemcatalog.item.dto.BrandUpdateRequest;
import com.link.itemcatalog.item.model.entity.Brand;

/**
 * Mapper for converting brand entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BrandMapper {

    Brand toEntity(BrandCreateRequest dto);

    void update(@MappingTarget Brand entity, BrandUpdateRequest dto);

    BrandResponse toResponse(Brand entity);
}
