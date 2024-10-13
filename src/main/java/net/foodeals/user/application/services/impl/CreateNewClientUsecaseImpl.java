package net.foodeals.user.application.services.impl;

import lombok.RequiredArgsConstructor;
import net.foodeals.common.annotations.UseCase;
import net.foodeals.user.application.dtos.requests.ClientRegisterRequest;
import net.foodeals.user.application.services.RoleService;
import net.foodeals.user.application.services.CreateNewClientUsecase;
import net.foodeals.user.domain.entities.Role;
import net.foodeals.user.domain.entities.User;
import net.foodeals.user.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
 class CreateNewClientUsecaseImpl implements CreateNewClientUsecase {

    private final UserRepository repository;
    private final RoleService roleservice;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User execute(ClientRegisterRequest request) {
        final Role role = roleservice.findByName("CLIENT");
        final User user = mapper.map(request, User.class)
                .setPassword(passwordEncoder.encode(request.password()))
                .setRole(role);
        return repository.save(user);
    }
}
