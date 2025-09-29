package com.link.itemcatalog.item.dto;

import java.time.Instant;
import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.GenericStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing a brand.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long id;

    private String code;

    private Map<String, String> name;

    private Map<String, String> description;

    private GenericStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
