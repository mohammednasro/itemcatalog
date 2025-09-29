package com.link.itemcatalog.item.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request payload to upsert an item attribute value.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemAttributeValueUpsertRequest {

    @NotNull
    private Long itemId;

    @NotNull
    private Long attributeId;

    private String valueString;

    private BigDecimal valueNumber;

    private Boolean valueBoolean;

    private LocalDate valueDate;

    private String valueJson;
}
