package net.foodeals.location.application.dtos.responses;

import net.foodeals.common.valueOjects.Coordinates;

import java.util.UUID;

public record AddressResponse(UUID id, String address, String extraAddress, String zip, Coordinates coordinates,
                              CityResponse city) {
}
