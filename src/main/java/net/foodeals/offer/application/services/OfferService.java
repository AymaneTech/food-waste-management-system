package net.foodeals.offer.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.offer.application.dtos.requests.OfferRequest;
import net.foodeals.offer.domain.entities.Offer;

import java.util.UUID;

public interface OfferService extends CrudService<Offer, UUID, OfferRequest> {
}
