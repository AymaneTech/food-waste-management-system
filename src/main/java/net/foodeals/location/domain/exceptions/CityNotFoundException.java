package net.foodeals.location.domain.exceptions;

import java.util.UUID;

public class CityNotFoundException extends RuntimeException {

    private final UUID id;
    private final String name;

    public CityNotFoundException(UUID id) {
        super("city with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public CityNotFoundException(String name) {
        super("city with name " + name + " not found");
        this.id = null;
        this.name = name;
    }
}
