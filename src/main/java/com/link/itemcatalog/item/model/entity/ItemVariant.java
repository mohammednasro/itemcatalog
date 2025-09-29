package com.link.itemcatalog.item.model.entity;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.link.itemcatalog.item.model.base.BaseEntity;
import com.link.itemcatalog.item.model.enumtype.VariantStatus;
import com.link.itemcatalog.item.model.value.Dimensions;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@ToString(exclude = {"item", "optionValues"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_variants",
        indexes = {
                @Index(name = "idx_item_variant_item", columnList = "item_id"),
                @Index(name = "idx_item_variant_status", columnList = "status")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_item_variant_sku", columnNames = {"sku"})
        })
public class ItemVariant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "sku", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String sku;

    @Column(name = "barcode")
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VariantStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "option_values", columnDefinition = "json")
    private Map<String, String> optionValues;

    @Column(name = "attributes_hash")
    private String attributesHash;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Embedded
    private Dimensions dimensions;
}
