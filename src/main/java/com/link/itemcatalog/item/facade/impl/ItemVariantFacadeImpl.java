package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.ItemVariantCreateRequest;
import com.link.itemcatalog.item.dto.ItemVariantResponse;
import com.link.itemcatalog.item.dto.ItemVariantUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.exception.ConflictException;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.ItemVariantMapper;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemVariant;
import com.link.itemcatalog.item.model.repository.ItemRepository;
import com.link.itemcatalog.item.model.repository.ItemVariantRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.ItemVariantFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemVariantFacadeImpl implements com.link.itemcatalog.item.facade.ItemVariantFacade {

    private final ItemVariantRepository itemVariantRepository;

    private final ItemRepository itemRepository;

    private final ItemVariantMapper itemVariantMapper;

    @Override
    public ItemVariantResponse create(ItemVariantCreateRequest request) {
        Optional<ItemVariant> existing = itemVariantRepository.findBySku(request.getSku());
        if (existing.isPresent()) {
            throw new ConflictException("Item variant with SKU '%s' already exists.".formatted(request.getSku()));
        }
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new NotFoundException("Item with id '%d' not found.".formatted(request.getItemId())));
        ItemVariant variant = itemVariantMapper.toEntity(request);
        variant.setItem(item);
        ItemVariant saved = itemVariantRepository.save(variant);
        return itemVariantMapper.toResponse(saved);
    }

    @Override
    public ItemVariantResponse update(Long id, ItemVariantUpdateRequest request) {
        ItemVariant variant = findById(id);
        itemVariantMapper.update(variant, request);
        ItemVariant saved = itemVariantRepository.save(variant);
        return itemVariantMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemVariantResponse getById(Long id) {
        return itemVariantMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ItemVariantResponse getBySku(String sku) {
        ItemVariant variant = itemVariantRepository.findBySku(sku)
                .orElseThrow(() -> new NotFoundException("Item variant with SKU '%s' not found.".formatted(sku)));
        return itemVariantMapper.toResponse(variant);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ItemVariantResponse> list(Pageable pageable) {
        Page<ItemVariant> page = itemVariantRepository.findAll(pageable);
        Page<ItemVariantResponse> mapped = page.map(itemVariantMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        ItemVariant variant = findById(id);
        itemVariantRepository.delete(variant);
    }

    private ItemVariant findById(Long id) {
        return itemVariantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item variant with id '%d' not found.".formatted(id)));
    }
}
