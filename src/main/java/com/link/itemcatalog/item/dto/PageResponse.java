package com.link.itemcatalog.item.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Generic pagination response wrapper.
 *
 * @param <T> type of the content elements
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;

    private long totalElements;

    private int totalPages;

    private int page;

    private int size;
}
