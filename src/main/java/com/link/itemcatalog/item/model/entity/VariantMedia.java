package com.link.itemcatalog.item.model.entity;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.link.itemcatalog.item.model.base.BaseEntity;
import com.link.itemcatalog.item.model.enumtype.MediaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@ToString(exclude = {"variant", "altText"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "variant_media",
        indexes = {
                @Index(name = "idx_variant_media_variant", columnList = "variant_id"),
                @Index(name = "idx_variant_media_type", columnList = "type")
        })
public class VariantMedia extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    @EqualsAndHashCode.Include
    private ItemVariant variant;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MediaType type;

    @Column(name = "url", nullable = false)
    @EqualsAndHashCode.Include
    private String url;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "alt_text", columnDefinition = "json")
    private Map<String, String> altText;

    @Column(name = "priority")
    private Integer priority;
}
