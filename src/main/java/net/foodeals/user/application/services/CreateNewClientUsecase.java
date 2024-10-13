package net.foodeals.user.application.services;

import net.foodeals.common.contracts.UseCase;
import net.foodeals.user.application.dtos.requests.ClientRegisterRequest;
import net.foodeals.user.domain.entities.User;

public interface CreateNewClientUsecase extends UseCase<ClientRegisterRequest, User> {
}
