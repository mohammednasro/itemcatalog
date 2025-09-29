package com.link.itemcatalog.item.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.link.itemcatalog.item.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"variant", "attribute"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "variant_attribute_values",
        indexes = {
                @Index(name = "idx_variant_attribute_value_attribute", columnList = "attribute_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_variant_attribute_value", columnNames = {"variant_id", "attribute_id"})
        })
public class VariantAttributeValue extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    @EqualsAndHashCode.Include
    private ItemVariant variant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_id", nullable = false)
    @EqualsAndHashCode.Include
    private AttributeDefinition attribute;

    @Column(name = "value_string")
    private String valueString;

    @Column(name = "value_number")
    private BigDecimal valueNumber;

    @Column(name = "value_boolean")
    private Boolean valueBoolean;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @Column(name = "value_json", columnDefinition = "json")
    private String valueJson;
}
