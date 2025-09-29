package com.link.itemcatalog.item.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.ItemAttributeValue;

public interface ItemAttributeValueRepository extends JpaRepository<ItemAttributeValue, Long> {

    Optional<ItemAttributeValue> findByItem_IdAndAttribute_Id(Long itemId, Long attributeId);
}
