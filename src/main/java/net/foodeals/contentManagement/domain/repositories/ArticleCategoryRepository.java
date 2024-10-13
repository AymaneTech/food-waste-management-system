package net.foodeals.contentManagement.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.common.contracts.SlugRepository;
import net.foodeals.contentManagement.domain.entities.ArticleCategory;

import java.util.UUID;

public interface ArticleCategoryRepository extends BaseRepository<ArticleCategory, UUID>, SlugRepository {
}
