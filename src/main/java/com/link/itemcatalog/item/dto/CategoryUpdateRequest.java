package com.link.itemcatalog.item.dto;

import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.GenericStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to update a category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryUpdateRequest {

    private Map<String, String> name;

    private Map<String, String> description;

    private Long parentId;

    private Integer sortOrder;

    @NotNull
    private GenericStatus status;
}
