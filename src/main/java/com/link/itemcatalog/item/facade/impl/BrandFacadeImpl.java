package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.BrandCreateRequest;
import com.link.itemcatalog.item.dto.BrandResponse;
import com.link.itemcatalog.item.dto.BrandUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.exception.ConflictException;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.mapper.BrandMapper;
import com.link.itemcatalog.item.model.entity.Brand;
import com.link.itemcatalog.item.model.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.BrandFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BrandFacadeImpl implements com.link.itemcatalog.item.facade.BrandFacade {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    @Override
    public BrandResponse create(BrandCreateRequest request) {
        Optional<Brand> existing = brandRepository.findByCode(request.getCode());
        if (existing.isPresent()) {
            throw new ConflictException("Brand with code '%s' already exists.".formatted(request.getCode()));
        }
        Brand brand = brandMapper.toEntity(request);
        Brand saved = brandRepository.save(brand);
        return brandMapper.toResponse(saved);
    }

    @Override
    public BrandResponse update(Long id, BrandUpdateRequest request) {
        Brand brand = findById(id);
        brandMapper.update(brand, request);
        Brand saved = brandRepository.save(brand);
        return brandMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponse getById(Long id) {
        return brandMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponse getByCode(String code) {
        Brand brand = brandRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Brand with code '%s' not found.".formatted(code)));
        return brandMapper.toResponse(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BrandResponse> list(Pageable pageable) {
        Page<Brand> page = brandRepository.findAll(pageable);
        Page<BrandResponse> mapped = page.map(brandMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        Brand brand = findById(id);
        brandRepository.delete(brand);
    }

    private Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand with id '%d' not found.".formatted(id)));
    }
}
