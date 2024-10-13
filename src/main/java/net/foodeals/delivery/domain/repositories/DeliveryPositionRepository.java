package net.foodeals.delivery.domain.repositories;

import net.foodeals.delivery.domain.entities.DeliveryPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryPositionRepository extends JpaRepository<DeliveryPosition, UUID> {
}
