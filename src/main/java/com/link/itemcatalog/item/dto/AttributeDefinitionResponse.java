package com.link.itemcatalog.item.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.AttributeDataType;
import com.link.itemcatalog.item.model.enumtype.AttributeScope;
import com.link.itemcatalog.item.model.enumtype.GenericStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing an attribute definition.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeDefinitionResponse {

    private Long id;

    private String code;

    private Map<String, String> name;

    private AttributeDataType dataType;

    private List<String> allowedValues;

    private AttributeScope scope;

    private Boolean required;

    private Boolean searchable;

    private Boolean facetable;

    private String unit;

    private GenericStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
