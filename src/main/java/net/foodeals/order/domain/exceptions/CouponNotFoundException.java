package net.foodeals.order.domain.exceptions;

import java.util.UUID;

public class CouponNotFoundException extends RuntimeException{

    private final UUID id;

    public CouponNotFoundException(UUID id){
        super("coupon with id " + id + "not found");
        this.id = id;
    }
}
