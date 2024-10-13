package net.foodeals.product.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.product.application.dtos.requests.ProductRequest;
import net.foodeals.product.application.services.ProductCategoryService;
import net.foodeals.product.application.services.ProductService;
import net.foodeals.product.domain.entities.Product;
import net.foodeals.product.domain.entities.ProductCategory;
import net.foodeals.product.domain.exceptions.ProductNotFoundException;
import net.foodeals.product.domain.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
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
class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductCategoryService categoryService;
    private final ModelMapper mapper;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Product findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product create(ProductRequest request) {
        final ProductCategory category = categoryService.findById(request.categoryId());
        final Product product = mapper.map(request, Product.class);

        product.setCategory(category);
        product.setSlug(makeUniqueSlug(toSlug(request.name()), repository));

        return repository.save(product);
    }

    @Override
    public Product update(UUID id, ProductRequest request) {
        final Product product = findById(id);
        final ProductCategory category = categoryService.findById(request.categoryId());

        mapper.map(request, product);
        product.setCategory(category);
        product.setSlug(makeUniqueSlug(toSlug(request.name()), repository));

        return repository.save(product);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new ProductNotFoundException(id);

        repository.softDelete(id);
    }
}
