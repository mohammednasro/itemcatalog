package com.link.itemcatalog.item.facade.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.itemcatalog.item.dto.CategoryCreateRequest;
import com.link.itemcatalog.item.dto.CategoryResponse;
import com.link.itemcatalog.item.dto.CategoryUpdateRequest;
import com.link.itemcatalog.item.dto.PageResponse;
import com.link.itemcatalog.item.exception.ConflictException;
import com.link.itemcatalog.item.exception.NotFoundException;
import com.link.itemcatalog.item.exception.ValidationException;
import com.link.itemcatalog.item.mapper.CategoryMapper;
import com.link.itemcatalog.item.model.entity.Category;
import com.link.itemcatalog.item.model.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

/**
 * Default implementation of {@link com.link.itemcatalog.item.facade.CategoryFacade}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryFacadeImpl implements com.link.itemcatalog.item.facade.CategoryFacade {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {
        Optional<Category> existing = categoryRepository.findByCode(request.getCode());
        if (existing.isPresent()) {
            throw new ConflictException("Category with code '%s' already exists.".formatted(request.getCode()));
        }
        Category parent = resolveParent(request.getParentId());
        Category category = categoryMapper.toEntity(request);
        category.setParent(parent);
        applyHierarchy(category);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    @Override
    public CategoryResponse update(Long id, CategoryUpdateRequest request) {
        Category category = findById(id);
        categoryMapper.update(category, request);
        Category parent = resolveParent(request.getParentId());
        if (parent != null && parent.getId().equals(category.getId())) {
            throw new ValidationException("Category cannot be its own parent.");
        }
        category.setParent(parent);
        applyHierarchy(category);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getById(Long id) {
        return categoryMapper.toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getByCode(String code) {
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Category with code '%s' not found.".formatted(code)));
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CategoryResponse> list(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        Page<CategoryResponse> mapped = page.map(categoryMapper::toResponse);
        return PaginationUtils.fromPage(mapped);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id '%d' not found.".formatted(id)));
    }

    private Category resolveParent(Long parentId) {
        if (parentId == null) {
            return null;
        }
        return categoryRepository.findById(parentId)
                .orElseThrow(() -> new NotFoundException("Parent category with id '%d' not found.".formatted(parentId)));
    }

    private void applyHierarchy(Category category) {
        Category parent = category.getParent();
        if (parent == null) {
            category.setPath("/" + category.getCode());
            category.setDepth(0);
            return;
        }
        String parentPath = parent.getPath();
        if (parentPath == null) {
            parentPath = "/" + parent.getCode();
        }
        Integer parentDepth = parent.getDepth();
        int depth = parentDepth != null ? parentDepth + 1 : 1;
        category.setPath(parentPath + "/" + category.getCode());
        category.setDepth(depth);
    }
}
