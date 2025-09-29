package com.link.itemcatalog.item.dto;

import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.MediaType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to create item media.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemMediaCreateRequest {

    @NotNull
    private Long itemId;

    @NotNull
    private MediaType type;

    @NotBlank
    private String url;

    private Map<String, String> altText;

    private Integer priority;
}
