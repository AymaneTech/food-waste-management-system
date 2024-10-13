package net.foodeals.user.domain.exceptions;

import java.util.UUID;

public class RoleNotFoundException extends RuntimeException {

    private final UUID id;
    private final String name;

    public RoleNotFoundException(UUID id) {
        super("role with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public RoleNotFoundException(String name) {
        super("role with name " + name + " not found");
        this.name = name;
        this.id = null;
    }
}
