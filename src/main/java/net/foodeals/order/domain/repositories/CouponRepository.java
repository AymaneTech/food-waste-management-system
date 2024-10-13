package net.foodeals.order.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.order.domain.entities.Coupon;

import java.util.UUID;

public interface CouponRepository extends BaseRepository<Coupon, UUID> {
}
