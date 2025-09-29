package com.link.itemcatalog.item.dto;

import java.util.List;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.AttributeDataType;
import com.link.itemcatalog.item.model.enumtype.AttributeScope;
import com.link.itemcatalog.item.model.enumtype.GenericStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to update an attribute definition.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeDefinitionUpdateRequest {

    private Map<String, String> name;

    @NotNull
    private AttributeDataType dataType;

    private List<String> allowedValues;

    @NotNull
    private AttributeScope scope;

    private Boolean required;

    private Boolean searchable;

    private Boolean facetable;

    private String unit;

    @NotNull
    private GenericStatus status;
}
