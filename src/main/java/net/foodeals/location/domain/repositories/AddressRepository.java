package net.foodeals.location.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.location.domain.entities.Address;

import java.util.UUID;

public interface AddressRepository extends BaseRepository<Address, UUID> {
}
