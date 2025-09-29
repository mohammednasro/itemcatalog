package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.AttributeDefinitionCreateRequest;
import com.link.itemcatalog.item.dto.AttributeDefinitionResponse;
import com.link.itemcatalog.item.dto.AttributeDefinitionUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.exception.ConflictException;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.AttributeDefinitionMapper;
import com.link.itemcatalog.item.model.entity.AttributeDefinition;
import com.link.itemcatalog.item.model.repository.AttributeDefinitionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.AttributeDefinitionFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AttributeDefinitionFacadeImpl implements com.link.itemcatalog.item.facade.AttributeDefinitionFacade {

    private final AttributeDefinitionRepository attributeDefinitionRepository;

    private final AttributeDefinitionMapper attributeDefinitionMapper;

    @Override
    public AttributeDefinitionResponse create(AttributeDefinitionCreateRequest request) {
        Optional<AttributeDefinition> existing = attributeDefinitionRepository.findByCode(request.getCode());
        if (existing.isPresent()) {
            throw new ConflictException("Attribute definition with code '%s' already exists.".formatted(request.getCode()));
        }
        AttributeDefinition entity = attributeDefinitionMapper.toEntity(request);
        AttributeDefinition saved = attributeDefinitionRepository.save(entity);
        return attributeDefinitionMapper.toResponse(saved);
    }

    @Override
    public AttributeDefinitionResponse update(Long id, AttributeDefinitionUpdateRequest request) {
        AttributeDefinition entity = findById(id);
        attributeDefinitionMapper.update(entity, request);
        AttributeDefinition saved = attributeDefinitionRepository.save(entity);
        return attributeDefinitionMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AttributeDefinitionResponse getById(Long id) {
        return attributeDefinitionMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public AttributeDefinitionResponse getByCode(String code) {
        AttributeDefinition entity = attributeDefinitionRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Attribute definition with code '%s' not found.".formatted(code)));
        return attributeDefinitionMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<AttributeDefinitionResponse> list(Pageable pageable) {
        Page<AttributeDefinition> page = attributeDefinitionRepository.findAll(pageable);
        Page<AttributeDefinitionResponse> mapped = page.map(attributeDefinitionMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        AttributeDefinition entity = findById(id);
        attributeDefinitionRepository.delete(entity);
    }

    private AttributeDefinition findById(Long id) {
        return attributeDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attribute definition with id '%d' not found.".formatted(id)));
    }
}
