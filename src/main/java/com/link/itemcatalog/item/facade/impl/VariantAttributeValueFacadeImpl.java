package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.VariantAttributeValueResponse;
import com.link.itemcatalog.item.dto.VariantAttributeValueUpsertRequest;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.VariantAttributeValueMapper;
import com.link.itemcatalog.item.model.entity.AttributeDefinition;
import com.link.itemcatalog.item.model.entity.ItemVariant;
import com.link.itemcatalog.item.model.entity.VariantAttributeValue;
import com.link.itemcatalog.item.model.repository.AttributeDefinitionRepository;
import com.link.itemcatalog.item.model.repository.ItemVariantRepository;
import com.link.itemcatalog.item.model.repository.VariantAttributeValueRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.VariantAttributeValueFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VariantAttributeValueFacadeImpl implements com.link.itemcatalog.item.facade.VariantAttributeValueFacade {

    private final VariantAttributeValueRepository variantAttributeValueRepository;

    private final ItemVariantRepository itemVariantRepository;

    private final AttributeDefinitionRepository attributeDefinitionRepository;

    private final VariantAttributeValueMapper variantAttributeValueMapper;

    @Override
    public VariantAttributeValueResponse upsert(VariantAttributeValueUpsertRequest request) {
        ItemVariant variant = itemVariantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new NotFoundException("Item variant with id '%d' not found.".formatted(request.getVariantId())));
        AttributeDefinition attribute = attributeDefinitionRepository.findById(request.getAttributeId())
                .orElseThrow(() -> new NotFoundException("Attribute definition with id '%d' not found.".formatted(request.getAttributeId())));
        Optional<VariantAttributeValue> existing = variantAttributeValueRepository.findByVariant_IdAndAttribute_Id(variant.getId(), attribute.getId());
        VariantAttributeValue entity;
        if (existing.isPresent()) {
            entity = existing.get();
            variantAttributeValueMapper.update(entity, request);
        } else {
            entity = variantAttributeValueMapper.toEntity(request);
        }
        entity.setVariant(variant);
        entity.setAttribute(attribute);
        VariantAttributeValue saved = variantAttributeValueRepository.save(entity);
        return variantAttributeValueMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        VariantAttributeValue entity = variantAttributeValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Variant attribute value with id '%d' not found.".formatted(id)));
        variantAttributeValueRepository.delete(entity);
    }
}
