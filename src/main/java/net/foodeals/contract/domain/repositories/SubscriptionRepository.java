package net.foodeals.contract.domain.repositories;

import net.foodeals.contract.domain.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
