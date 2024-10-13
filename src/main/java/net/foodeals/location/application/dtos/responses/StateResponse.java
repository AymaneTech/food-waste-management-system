package net.foodeals.location.application.dtos.responses;

import java.util.UUID;

public record StateResponse(UUID id, String name, String code, CountryResponse country) {
}
