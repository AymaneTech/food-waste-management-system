package net.foodeals.organizationEntity.domain.repositories;

import net.foodeals.organizationEntity.domain.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationEntityRepository extends JpaRepository<OrganizationEntity, UUID> {
}
