package net.foodeals.offer.application.services;

import net.foodeals.offer.application.dtos.requests.BoxDto;
import net.foodeals.offer.domain.entities.Box;

import java.util.UUID;

public interface BoxService {
    Box create(BoxDto dto);

    Box findById(UUID id);

    void delete(UUID id);
}
