package net.foodeals.location.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.location.application.dtos.requests.AddressRequest;
import net.foodeals.location.domain.entities.Address;

import java.util.UUID;

public interface AddressService extends CrudService<Address, UUID, AddressRequest> {
}
