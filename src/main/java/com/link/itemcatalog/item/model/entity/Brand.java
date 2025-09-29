package com.link.itemcatalog.item.model.entity;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.link.itemcatalog.item.model.base.BaseEntity;
import com.link.itemcatalog.item.model.enumtype.GenericStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Index;
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
@ToString(exclude = {"description"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "brands",
        indexes = {
                @Index(name = "idx_brand_status", columnList = "status")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_brand_code", columnNames = {"code"})
        })
public class Brand extends BaseEntity {

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
    private GenericStatus status;
}
