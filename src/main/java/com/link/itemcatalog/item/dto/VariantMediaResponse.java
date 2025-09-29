package com.link.itemcatalog.item.dto;

import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.MediaType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing variant media.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantMediaResponse {

    private Long id;

    private Long variantId;

    private MediaType type;

    private String url;

    private Map<String, String> altText;

    private Integer priority;
}
