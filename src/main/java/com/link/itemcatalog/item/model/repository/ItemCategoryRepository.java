package com.link.itemcatalog.item.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
}
