package net.foodeals.offer.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.offer.domain.entities.Deal;

import java.util.UUID;

public interface DealRepository extends BaseRepository<Deal, UUID> {
}
