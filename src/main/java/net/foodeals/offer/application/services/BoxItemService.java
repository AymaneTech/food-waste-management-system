package net.foodeals.offer.application.services;

import net.foodeals.offer.application.dtos.requests.BoxItemDto;
import net.foodeals.offer.domain.entities.Box;
import net.foodeals.offer.domain.entities.BoxItem;

import java.util.List;

public interface BoxItemService {
    List<BoxItem> createAll(List<BoxItemDto> dtos, Box box);
}
