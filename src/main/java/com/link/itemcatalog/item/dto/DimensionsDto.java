package com.link.itemcatalog.item.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object representing physical dimensions.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensionsDto {

    @DecimalMin(value = "0", inclusive = true)
    private BigDecimal length;

    @DecimalMin(value = "0", inclusive = true)
    private BigDecimal width;

    @DecimalMin(value = "0", inclusive = true)
    private BigDecimal height;

    private String unit;
}
