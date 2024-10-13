package net.foodeals.order.domain.exceptions;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException{

    private final UUID id;

    public TransactionNotFoundException(UUID id){
        super("transaction with id " + id + "not found");
        this.id = id;
    }
}
