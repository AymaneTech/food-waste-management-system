package net.foodeals.offer.application.services;

import net.foodeals.offer.application.dtos.requests.DealDto;
import net.foodeals.offer.domain.entities.Deal;

import java.util.UUID;

public interface DealService {

    Deal create(DealDto dto);

    Deal update(UUID id, DealDto dto);

    Deal findById(UUID id);

    void delete(UUID id);
}
