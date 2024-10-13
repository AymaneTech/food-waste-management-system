package net.foodeals.product.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.product.application.dtos.requests.ProductCategoryRequest;
import net.foodeals.product.domain.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryService extends CrudService<ProductCategory, UUID, ProductCategoryRequest> {
}
