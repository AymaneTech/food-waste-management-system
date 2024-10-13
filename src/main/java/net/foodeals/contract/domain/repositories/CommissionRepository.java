package net.foodeals.contract.domain.repositories;

import net.foodeals.contract.domain.entities.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommissionRepository extends JpaRepository<Commission, UUID> {
}
