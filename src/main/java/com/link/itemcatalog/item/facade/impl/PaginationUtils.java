package com.link.itemcatalog.item.facade.impl;

import org.springframework.data.domain.Page;

import com.link.itemcatalog.item.dto.PageResponse;

/**
 * Utility methods for pagination conversions.
 */
public final class PaginationUtils {

    private PaginationUtils() {
    }

    /**
     * Converts a {@link Page} into a {@link PageResponse} instance.
     *
     * @param page the source page
     * @param <T> type of the page content
     * @return page response with metadata copied from the page
     */
    public static <T> PageResponse<T> fromPage(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .size(page.getSize())
                .build();
    }
}
