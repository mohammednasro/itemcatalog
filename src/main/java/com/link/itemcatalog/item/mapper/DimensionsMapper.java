package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.DimensionsDto;
import com.link.itemcatalog.item.model.value.Dimensions;

/**
 * Mapper for converting between {@link Dimensions} and {@link DimensionsDto}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DimensionsMapper {

    Dimensions toEntity(DimensionsDto dto);

    DimensionsDto toDto(Dimensions dimensions);
}
