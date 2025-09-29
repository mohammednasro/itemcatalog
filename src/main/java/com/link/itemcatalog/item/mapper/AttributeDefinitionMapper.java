package com.link.itemcatalog.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.link.itemcatalog.item.dto.AttributeDefinitionCreateRequest;
import com.link.itemcatalog.item.dto.AttributeDefinitionResponse;
import com.link.itemcatalog.item.dto.AttributeDefinitionUpdateRequest;
import com.link.itemcatalog.item.model.entity.AttributeDefinition;

/**
 * Mapper for converting attribute definition entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AttributeDefinitionMapper {

    AttributeDefinition toEntity(AttributeDefinitionCreateRequest dto);

    void update(@MappingTarget AttributeDefinition entity, AttributeDefinitionUpdateRequest dto);

    AttributeDefinitionResponse toResponse(AttributeDefinition entity);
}
