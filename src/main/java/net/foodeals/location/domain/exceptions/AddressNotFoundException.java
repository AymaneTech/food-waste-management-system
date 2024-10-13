package net.foodeals.location.domain.exceptions;

import java.util.UUID;

public class AddressNotFoundException extends RuntimeException {

    private final UUID id;
    private final String name;

    public AddressNotFoundException(UUID id) {
        super("address with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public AddressNotFoundException(String name) {
        super("address with name " + name + " not found");
        this.id = null;
        this.name = name;
    }
}
