package net.foodeals.common.contracts;

public interface SlugRepository {
    Boolean existsBySlug(String slug);
}
