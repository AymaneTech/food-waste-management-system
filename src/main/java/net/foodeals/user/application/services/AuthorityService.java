package net.foodeals.user.application.services;

import net.foodeals.common.contracts.CrudService;
import net.foodeals.user.application.dtos.requests.AuthorityRequest;
import net.foodeals.user.domain.entities.Authority;

import java.util.UUID;

public interface AuthorityService extends CrudService<Authority, UUID, AuthorityRequest> {

}
