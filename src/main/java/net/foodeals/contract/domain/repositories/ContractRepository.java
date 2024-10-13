package net.foodeals.contract.domain.repositories;

import net.foodeals.contract.domain.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
}
