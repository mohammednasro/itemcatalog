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
 * Response payload representing a category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;

    private String code;

    private Map<String, String> name;

    private Map<String, String> description;

    private Long parentId;

    private String path;

    private Integer depth;

    private Integer sortOrder;

    private GenericStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
