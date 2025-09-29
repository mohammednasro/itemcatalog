package com.link.itemcatalog.item.model.value;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Dimensions {

    @DecimalMin(value = "0", inclusive = true)
    @Column(name = "length")
    private BigDecimal length;

    @DecimalMin(value = "0", inclusive = true)
    @Column(name = "width")
    private BigDecimal width;

    @DecimalMin(value = "0", inclusive = true)
    @Column(name = "height")
    private BigDecimal height;

    @Column(name = "dimension_unit")
    private String unit;
}
