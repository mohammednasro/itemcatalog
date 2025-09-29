package com.link.itemcatalog.item.dto;

import java.time.Instant;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.ItemStatus;
import com.link.itemcatalog.item.model.enumtype.SkuPolicy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing an item.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {

    private Long id;

    private String code;

    private Map<String, String> name;

    private Map<String, String> description;

    private ItemStatus status;

    private Long brandId;

    private SkuPolicy skuPolicy;

    private Long defaultVariantId;

    private Integer sortOrder;

    private Instant createdAt;

    private Instant updatedAt;
}
