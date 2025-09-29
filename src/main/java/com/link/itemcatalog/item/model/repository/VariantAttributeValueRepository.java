package com.link.itemcatalog.item.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.VariantAttributeValue;

public interface VariantAttributeValueRepository extends JpaRepository<VariantAttributeValue, Long> {

    Optional<VariantAttributeValue> findByVariant_IdAndAttribute_Id(Long variantId, Long attributeId);
}
