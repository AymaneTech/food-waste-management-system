package net.foodeals.product.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.organizationEntity.application.services.ActivityService;
import net.foodeals.organizationEntity.domain.entities.Activity;
import net.foodeals.product.application.dtos.requests.ProductCategoryRequest;
import net.foodeals.product.application.services.ProductCategoryService;
import net.foodeals.product.domain.entities.ProductCategory;
import net.foodeals.product.domain.exceptions.ProductCategoryNotFoundException;
import net.foodeals.product.domain.repositories.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static net.foodeals.common.Utils.SlugUtil.makeUniqueSlug;
import static net.foodeals.common.Utils.SlugUtil.toSlug;

@Service
@Transactional
@RequiredArgsConstructor
class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository repository;
    private final ActivityService activityService;

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<ProductCategory> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public ProductCategory findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));
    }

    @Override
    public ProductCategory create(ProductCategoryRequest request) {
        final Activity activity = activityService.findById(request.activityId());

        final String slug = makeUniqueSlug(toSlug(request.name()), repository);
        final ProductCategory productCategory = ProductCategory.create(request.name(), slug, activity);

        return repository.save(productCategory);
    }

    @Override
    public ProductCategory update(UUID id, ProductCategoryRequest request) {
        final ProductCategory productCategory = findById(id);
        final Activity activity = activityService.findById(request.activityId());

        productCategory
                .setName(request.name())
                .setSlug(makeUniqueSlug(toSlug(request.name()), repository))
                .setActivity(activity);

        return repository.save(productCategory);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new ProductCategoryNotFoundException(id);

        repository.softDelete(id);
    }
}
