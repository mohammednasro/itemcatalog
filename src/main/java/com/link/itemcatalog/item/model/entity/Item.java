package com.link.itemcatalog.item.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.link.itemcatalog.item.model.base.BaseEntity;
import com.link.itemcatalog.item.model.enumtype.ItemStatus;
import com.link.itemcatalog.item.model.enumtype.SkuPolicy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
@ToString(exclude = {"brand", "variants", "description"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "items",
        indexes = {
                @Index(name = "idx_item_status", columnList = "status"),
                @Index(name = "idx_item_brand", columnList = "brand_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_item_code", columnNames = {"code"})
        })
public class Item extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String code;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "name", columnDefinition = "json")
    private Map<String, String> name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "description", columnDefinition = "json")
    private Map<String, String> description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ItemStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "sku_policy", nullable = false)
    private SkuPolicy skuPolicy;

    @Column(name = "default_variant_id")
    private Long defaultVariantId;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVariant> variants = new ArrayList<>();
}
