package net.foodeals.location.application.dtos.responses;

import java.util.UUID;

public record CountryResponse(UUID id, String name, String code) {
}
