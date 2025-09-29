package com.link.itemcatalog.item.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.dto.VariantMediaCreateRequest;
import com.link.itemcatalog.item.dto.VariantMediaResponse;
import com.link.itemcatalog.item.dto.VariantMediaUpdateRequest;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.VariantMediaMapper;
import com.link.itemcatalog.item.model.entity.ItemVariant;
import com.link.itemcatalog.item.model.entity.VariantMedia;
import com.link.itemcatalog.item.model.repository.ItemVariantRepository;
import com.link.itemcatalog.item.model.repository.VariantMediaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.VariantMediaFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VariantMediaFacadeImpl implements com.link.itemcatalog.item.facade.VariantMediaFacade {

    private final VariantMediaRepository variantMediaRepository;

    private final ItemVariantRepository itemVariantRepository;

    private final VariantMediaMapper variantMediaMapper;

    @Override
    public VariantMediaResponse create(VariantMediaCreateRequest request) {
        ItemVariant variant = itemVariantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new NotFoundException("Item variant with id '%d' not found.".formatted(request.getVariantId())));
        VariantMedia media = variantMediaMapper.toEntity(request);
        media.setVariant(variant);
        VariantMedia saved = variantMediaRepository.save(media);
        return variantMediaMapper.toResponse(saved);
    }

    @Override
    public VariantMediaResponse update(Long id, VariantMediaUpdateRequest request) {
        VariantMedia media = findById(id);
        variantMediaMapper.update(media, request);
        VariantMedia saved = variantMediaRepository.save(media);
        return variantMediaMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public VariantMediaResponse getById(Long id) {
        return variantMediaMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<VariantMediaResponse> list(Pageable pageable) {
        Page<VariantMedia> page = variantMediaRepository.findAll(pageable);
        Page<VariantMediaResponse> mapped = page.map(variantMediaMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        VariantMedia media = findById(id);
        variantMediaRepository.delete(media);
    }

    private VariantMedia findById(Long id) {
        return variantMediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Variant media with id '%d' not found.".formatted(id)));
    }
}
