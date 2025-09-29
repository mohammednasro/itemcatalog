package com.link.itemcatalog.item.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.VariantStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing an item variant.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVariantResponse {

    private Long id;

    private Long itemId;

    private String sku;

    private String barcode;

    private VariantStatus status;

    private Map<String, String> optionValues;

    private Boolean isDefault;

    private BigDecimal weight;

    private String weightUnit;

    private DimensionsDto dimensions;

    private Instant createdAt;

    private Instant updatedAt;
}
