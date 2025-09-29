package com.link.itemcatalog.item.dto;

import java.util.Map;

import com.link.itemcatalog.item.model.enumtype.MediaType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing item media.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemMediaResponse {

    private Long id;

    private Long itemId;

    private MediaType type;

    private String url;

    private Map<String, String> altText;

    private Integer priority;
}
