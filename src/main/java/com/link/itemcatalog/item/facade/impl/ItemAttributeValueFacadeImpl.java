package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.ItemAttributeValueResponse;
import com.link.itemcatalog.item.dto.ItemAttributeValueUpsertRequest;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.ItemAttributeValueMapper;
import com.link.itemcatalog.item.model.entity.AttributeDefinition;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemAttributeValue;
import com.link.itemcatalog.item.model.repository.AttributeDefinitionRepository;
import com.link.itemcatalog.item.model.repository.ItemAttributeValueRepository;
import com.link.itemcatalog.item.model.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.ItemAttributeValueFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemAttributeValueFacadeImpl implements com.link.itemcatalog.item.facade.ItemAttributeValueFacade {

    private final ItemAttributeValueRepository itemAttributeValueRepository;

    private final ItemRepository itemRepository;

    private final AttributeDefinitionRepository attributeDefinitionRepository;

    private final ItemAttributeValueMapper itemAttributeValueMapper;

    @Override
    public ItemAttributeValueResponse upsert(ItemAttributeValueUpsertRequest request) {
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new NotFoundException("Item with id '%d' not found.".formatted(request.getItemId())));
        AttributeDefinition attribute = attributeDefinitionRepository.findById(request.getAttributeId())
                .orElseThrow(() -> new NotFoundException("Attribute definition with id '%d' not found.".formatted(request.getAttributeId())));
        Optional<ItemAttributeValue> existing = itemAttributeValueRepository.findByItem_IdAndAttribute_Id(item.getId(), attribute.getId());
        ItemAttributeValue entity;
        if (existing.isPresent()) {
            entity = existing.get();
            itemAttributeValueMapper.update(entity, request);
        } else {
            entity = itemAttributeValueMapper.toEntity(request);
        }
        entity.setItem(item);
        entity.setAttribute(attribute);
        ItemAttributeValue saved = itemAttributeValueRepository.save(entity);
        return itemAttributeValueMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        ItemAttributeValue entity = itemAttributeValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item attribute value with id '%d' not found.".formatted(id)));
        itemAttributeValueRepository.delete(entity);
    }
}
