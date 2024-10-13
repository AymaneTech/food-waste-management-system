package net.foodeals.location.domain.exceptions;

import java.util.UUID;

public class CountryNotFoundException extends RuntimeException {

    private final UUID id;
    private final String name;

    public CountryNotFoundException(UUID id) {
        super("country with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public CountryNotFoundException(String name) {
        super("country with name " + name + " not found");
        this.id = null;
        this.name = name;
    }
}
