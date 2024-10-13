package net.foodeals.location.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.location.application.dtos.requests.CityRequest;
import net.foodeals.location.domain.entities.City;

import java.util.UUID;

public interface CityService extends CrudService<City, UUID, CityRequest> {
}
