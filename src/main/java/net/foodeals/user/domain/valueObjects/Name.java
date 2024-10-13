package net.foodeals.user.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Name(
        @Column(name = "first_name")
        String firstName,

        @Column(name = "last_name")
        String lastName
) {
}
