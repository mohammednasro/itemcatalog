package com.link.itemcatalog.item.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByCode(String code);
}
