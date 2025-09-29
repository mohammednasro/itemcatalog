package com.link.itemcatalog.item.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.VariantStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to create an item variant.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVariantCreateRequest {

    @NotNull
    private Long itemId;

    @NotBlank
    private String sku;

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
