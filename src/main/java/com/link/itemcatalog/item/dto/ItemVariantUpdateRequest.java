package com.link.itemcatalog.item.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.VariantStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to update an item variant.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVariantUpdateRequest {

    private String barcode;

    @NotNull
    private VariantStatus status;

    private Map<String, String> optionValues;

    private Boolean isDefault;

    private BigDecimal weight;

    private String weightUnit;

    @Valid
    private DimensionsDto dimensions;
}
