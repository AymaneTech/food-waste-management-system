package net.foodeals.product.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.common.contracts.SlugRepository;
import net.foodeals.product.domain.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryRepository extends BaseRepository<ProductCategory, UUID>, SlugRepository {
}
