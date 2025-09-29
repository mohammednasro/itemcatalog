package com.link.itemcatalog.item.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.VariantAttributeValue;

public interface VariantAttributeValueRepository extends JpaRepository<VariantAttributeValue, Long> {
}
