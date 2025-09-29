package com.link.itemcatalog.item.model.entity;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.link.itemcatalog.item.model.base.BaseEntity;
import com.link.itemcatalog.item.model.enumtype.AttributeDataType;
import com.link.itemcatalog.item.model.enumtype.AttributeScope;
import com.link.itemcatalog.item.model.enumtype.GenericStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
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
@ToString(exclude = {"name", "allowedValues"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "attribute_definitions",
        indexes = {
                @Index(name = "idx_attribute_definition_scope", columnList = "scope"),
                @Index(name = "idx_attribute_definition_status", columnList = "status")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_attribute_definition_code", columnNames = {"code"})
        })
public class AttributeDefinition extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String code;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "name", columnDefinition = "json")
    private Map<String, String> name;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type", nullable = false)
    private AttributeDataType dataType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "allowed_values", columnDefinition = "json")
    private List<String> allowedValues;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope", nullable = false)
    private AttributeScope scope;

    @Column(name = "required", nullable = false)
    private boolean required;

    @Column(name = "searchable", nullable = false)
    private boolean searchable;

    @Column(name = "facetable", nullable = false)
    private boolean facetable;

    @Column(name = "unit")
    private String unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GenericStatus status;
}
