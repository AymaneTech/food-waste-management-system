package net.foodeals.offer.application.dtos.requests;

import net.foodeals.offer.domain.enums.BoxType;

import java.util.List;

public record BoxDto(List<BoxItemDto> items, BoxType type) {
}
