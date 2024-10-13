package net.foodeals.offer.application.services;

import net.foodeals.offer.application.dtos.requests.OpenTimeDto;
import net.foodeals.offer.domain.entities.Offer;
import net.foodeals.offer.domain.entities.OpenTime;

public interface OpenTimeService {
    OpenTime create(OpenTimeDto dto, Offer offer);
}
