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
@ToString(exclude = {"item", "attribute"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_attribute_values",
        indexes = {
                @Index(name = "idx_item_attribute_value_attribute", columnList = "attribute_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_item_attribute_value", columnNames = {"item_id", "attribute_id"})
        })
public class ItemAttributeValue extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    @EqualsAndHashCode.Include
    private Item item;

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
