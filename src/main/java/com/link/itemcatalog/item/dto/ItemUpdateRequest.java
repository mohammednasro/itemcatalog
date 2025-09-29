package com.link.itemcatalog.item.dto;

import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.ItemStatus;
import com.link.itemcatalog.item.model.enumtype.SkuPolicy;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to update an item.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemUpdateRequest {

    private Map<String, String> name;

    private Map<String, String> description;

    @NotNull
    private ItemStatus status;

    private Long brandId;

    @NotNull
    private SkuPolicy skuPolicy;

    private Long defaultVariantId;

    private Integer sortOrder;
}
