package net.foodeals.offer.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import net.foodeals.common.valueOjects.Price;

import java.util.UUID;

public record DealDto(@NotNull UUID productId, @NotNull Integer quantity, @NotNull Price price) {
}
