package net.foodeals.user.domain.exceptions;

import java.util.UUID;

public class AuthorityNotFoundException extends RuntimeException {

    private final UUID id;
    private final String name;

    public AuthorityNotFoundException(UUID id) {
        super("authority with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public AuthorityNotFoundException(String name) {
        super("authority with name " + name + " not found");
        this.name = name;
        this.id = null;
    }
}
