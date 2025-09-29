package com.link.itemcatalog.item.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.ItemMediaCreateRequest;
import com.link.itemcatalog.item.dto.ItemMediaResponse;
import com.link.itemcatalog.item.dto.ItemMediaUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.ItemMediaMapper;
import com.link.itemcatalog.item.model.entity.Item;
import com.link.itemcatalog.item.model.entity.ItemMedia;
import com.link.itemcatalog.item.model.repository.ItemMediaRepository;
import com.link.itemcatalog.item.model.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.ItemMediaFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemMediaFacadeImpl implements com.link.itemcatalog.item.facade.ItemMediaFacade {

    private final ItemMediaRepository itemMediaRepository;

    private final ItemRepository itemRepository;

    private final ItemMediaMapper itemMediaMapper;

    @Override
    public ItemMediaResponse create(ItemMediaCreateRequest request) {
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new NotFoundException("Item with id '%d' not found.".formatted(request.getItemId())));
        ItemMedia media = itemMediaMapper.toEntity(request);
        media.setItem(item);
        if (media.getPriority() == null) {
            media.setPriority(0);
        }
        ItemMedia saved = itemMediaRepository.save(media);
        return itemMediaMapper.toResponse(saved);
    }

    @Override
    public ItemMediaResponse update(Long id, ItemMediaUpdateRequest request) {
        ItemMedia media = findById(id);
        itemMediaMapper.update(media, request);
        ItemMedia saved = itemMediaRepository.save(media);
        return itemMediaMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemMediaResponse getById(Long id) {
        return itemMediaMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ItemMediaResponse> list(Pageable pageable) {
        Page<ItemMedia> page = itemMediaRepository.findAll(pageable);
        Page<ItemMediaResponse> mapped = page.map(itemMediaMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        ItemMedia media = findById(id);
        itemMediaRepository.delete(media);
    }

    private ItemMedia findById(Long id) {
        return itemMediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item media with id '%d' not found.".formatted(id)));
    }
}
