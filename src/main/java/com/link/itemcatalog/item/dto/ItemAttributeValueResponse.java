package com.link.itemcatalog.item.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response payload representing an item attribute value.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemAttributeValueResponse {

    private Long id;

    private Long itemId;

    private Long attributeId;

    private String valueString;

    private BigDecimal valueNumber;

    private Boolean valueBoolean;

    private LocalDate valueDate;

    private String valueJson;
}
