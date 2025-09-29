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
 * Request payload to update a brand.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandUpdateRequest {

    private Map<String, String> name;

    private Map<String, String> description;

    @NotNull
    private GenericStatus status;
}
