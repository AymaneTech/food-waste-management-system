package net.foodeals.product.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.common.contracts.SlugRepository;
import net.foodeals.product.domain.entities.Product;

import java.util.UUID;

public interface ProductRepository extends BaseRepository<Product, UUID>, SlugRepository {
}
