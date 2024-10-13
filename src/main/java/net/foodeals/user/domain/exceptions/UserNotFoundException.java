package net.foodeals.user.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    private final Integer id;
    private final String email;

    public UserNotFoundException(Integer id) {
        super("user with id " + id + " not found");
        this.id = id;
        this.email = null;
    }

    public UserNotFoundException(String email) {
        super("user with email " + email + " not found");
        this.email = email;
        this.id = null;
    }
}
