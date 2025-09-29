package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.ItemCategoryLinkRequest;
import com.link.itemcatalog.item.dto.ItemCategoryResponse;
import com.link.itemcatalog.item.dto.ItemCreateRequest;
import com.link.itemcatalog.item.dto.ItemResponse;
import com.link.itemcatalog.item.dto.ItemUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.exception.ConflictException;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.exception.ValidationException;
import com.link.itemcatalog.item.mapper.ItemCategoryMapper;
import com.link.itemcatalog.item.mapper.ItemMapper;
import com.link.itemcatalog.item.model.entity.Brand;
import com.link.itemcatalog.item.model.entity.Category;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemCategory;
import com.link.itemcatalog.item.model.entity.ItemVariant;
import com.link.itemcatalog.item.model.repository.BrandRepository;
import com.link.itemcatalog.item.model.repository.CategoryRepository;
import com.link.itemcatalog.item.model.repository.ItemCategoryRepository;
import com.link.itemcatalog.item.model.repository.ItemRepository;
import com.link.itemcatalog.item.model.repository.ItemVariantRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.ItemFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemFacadeImpl implements com.link.itemcatalog.item.facade.ItemFacade {

    private final ItemRepository itemRepository;

    private final BrandRepository brandRepository;

    private final ItemVariantRepository itemVariantRepository;

    private final CategoryRepository categoryRepository;

    private final ItemCategoryRepository itemCategoryRepository;

    private final ItemMapper itemMapper;

    private final ItemCategoryMapper itemCategoryMapper;

    @Override
    public ItemResponse create(ItemCreateRequest request) {
        Optional<Item> existing = itemRepository.findByCode(request.getCode());
        if (existing.isPresent()) {
            throw new ConflictException("Item with code '%s' already exists.".formatted(request.getCode()));
        }
        Item item = itemMapper.toEntity(request);
        item.setBrand(resolveBrand(request.getBrandId()));
        Item saved = itemRepository.save(item);
        return itemMapper.toResponse(saved);
    }

    @Override
    public ItemResponse update(Long id, ItemUpdateRequest request) {
        Item item = findById(id);
        itemMapper.update(item, request);
        item.setBrand(resolveBrand(request.getBrandId()));
        applyDefaultVariant(item, request.getDefaultVariantId());
        Item saved = itemRepository.save(item);
        return itemMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemResponse getById(Long id) {
        return itemMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ItemResponse getByCode(String code) {
        Item item = itemRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Item with code '%s' not found.".formatted(code)));
        return itemMapper.toResponse(item);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ItemResponse> list(Pageable pageable) {
        Page<Item> page = itemRepository.findAll(pageable);
        Page<ItemResponse> mapped = page.map(itemMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }

    @Override
    public ItemCategoryResponse linkCategory(ItemCategoryLinkRequest request) {
        Item item = findById(request.getItemId());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with id '%d' not found.".formatted(request.getCategoryId())));
        boolean exists = itemCategoryRepository.existsByItem_IdAndCategory_Id(item.getId(), category.getId());
        if (exists) {
            throw new ConflictException("Item '%d' already linked to category '%d'.".formatted(item.getId(), category.getId()));
        }
        ItemCategory link = itemCategoryMapper.toEntity(request);
        link.setItem(item);
        link.setCategory(category);
        ItemCategory saved = itemCategoryRepository.save(link);
        return itemCategoryMapper.toResponse(saved);
    }

    @Override
    public void unlinkCategory(Long itemCategoryId) {
        ItemCategory link = itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new NotFoundException("Item category link with id '%d' not found.".formatted(itemCategoryId)));
        itemCategoryRepository.delete(link);
    }

    private Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item with id '%d' not found.".formatted(id)));
    }

    private Brand resolveBrand(Long brandId) {
        if (brandId == null) {
            return null;
        }
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand with id '%d' not found.".formatted(brandId)));
    }

    private void applyDefaultVariant(Item item, Long defaultVariantId) {
        if (defaultVariantId == null) {
            item.setDefaultVariantId(null);
            return;
        }
        ItemVariant variant = itemVariantRepository.findById(defaultVariantId)
                .orElseThrow(() -> new NotFoundException("Item variant with id '%d' not found.".formatted(defaultVariantId)));
        if (!variant.getItem().getId().equals(item.getId())) {
            throw new ValidationException("Variant '%d' does not belong to item '%d'.".formatted(defaultVariantId, item.getId()));
        }
        item.setDefaultVariantId(defaultVariantId);
    }
}
