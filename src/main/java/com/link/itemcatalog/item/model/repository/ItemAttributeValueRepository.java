package com.link.itemcatalog.item.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.ItemAttributeValue;

public interface ItemAttributeValueRepository extends JpaRepository<ItemAttributeValue, Long> {
}
