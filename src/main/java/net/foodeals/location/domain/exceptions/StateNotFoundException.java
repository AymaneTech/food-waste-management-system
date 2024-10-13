package net.foodeals.location.domain.exceptions;

import java.util.UUID;

public class StateNotFoundException extends RuntimeException {

    private final UUID id;
    private final String name;

    public StateNotFoundException(UUID id) {
        super("state with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public StateNotFoundException(String name) {
        super("state with name " + name + " not found");
        this.id = null;
        this.name = name;
    }
}
