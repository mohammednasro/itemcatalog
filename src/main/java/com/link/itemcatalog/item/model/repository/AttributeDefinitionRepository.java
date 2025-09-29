package com.link.itemcatalog.item.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.itemcatalog.item.model.entity.AttributeDefinition;

public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, Long> {
}
