package net.foodeals.location.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.location.application.dtos.requests.CountryRequest;
import net.foodeals.location.domain.entities.Country;

import java.util.UUID;

public interface CountryService extends CrudService<Country, UUID, CountryRequest> {
}
