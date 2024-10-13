package net.foodeals.location.application.dtos.responses;

import java.util.UUID;

public record CityResponse(UUID id, String name, String code, StateResponse state) {
}
