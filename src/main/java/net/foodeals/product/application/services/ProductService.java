package net.foodeals.product.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.product.application.dtos.requests.ProductRequest;
import net.foodeals.product.domain.entities.Product;

import java.util.UUID;

public interface ProductService extends CrudService<Product, UUID, ProductRequest> {
}
