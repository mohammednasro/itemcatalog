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
@ToString(exclude = {"item", "altText"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_media",
        indexes = {
                @Index(name = "idx_item_media_item", columnList = "item_id"),
                @Index(name = "idx_item_media_type", columnList = "type")
        })
public class ItemMedia extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    @EqualsAndHashCode.Include
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MediaType type;

    @Column(name = "url", nullable = false)
    @EqualsAndHashCode.Include
    private String url;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "alt_text", columnDefinition = "json")
    private Map<String, String> altText;

    @Default
    @Column(name = "priority", nullable = false)
    private Integer priority = 0;
}
