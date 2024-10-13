package net.foodeals.order.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.order.domain.entities.Transaction;

import java.util.UUID;

public interface TransactionRepository extends BaseRepository<Transaction, UUID> {
}
