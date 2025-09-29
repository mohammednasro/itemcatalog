package com.link.itemcatalog.item.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.ItemMedia;

public interface ItemMediaRepository extends JpaRepository<ItemMedia, Long> {
}
