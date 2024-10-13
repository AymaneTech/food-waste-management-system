package net.foodeals.location.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.location.domain.entities.Country;

import java.util.UUID;

public interface CountryRepository extends BaseRepository<Country, UUID> {
}
