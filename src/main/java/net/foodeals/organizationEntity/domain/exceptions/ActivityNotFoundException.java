package net.foodeals.organizationEntity.domain.exceptions;

import java.util.UUID;

public class ActivityNotFoundException extends RuntimeException{
    private final UUID id;

    public ActivityNotFoundException(UUID id) {
        super("Activity with id " + id + " not found");
        this.id = id;
    }
}
