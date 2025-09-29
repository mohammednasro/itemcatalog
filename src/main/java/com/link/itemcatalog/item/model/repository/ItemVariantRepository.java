package com.link.itemcatalog.item.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.ItemVariant;

public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {

    Optional<ItemVariant> findBySku(String sku);
}
