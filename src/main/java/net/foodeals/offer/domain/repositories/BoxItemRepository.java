package net.foodeals.offer.domain.repositories;

import net.foodeals.offer.domain.entities.BoxItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoxItemRepository extends JpaRepository<BoxItem, UUID> {
}
