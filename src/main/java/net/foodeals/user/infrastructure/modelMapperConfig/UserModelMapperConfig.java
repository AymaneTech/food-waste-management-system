package net.foodeals.user.infrastructure.modelMapperConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.responses.AuthorityResponse;
import net.foodeals.user.application.dtos.responses.RoleResponse;
import net.foodeals.user.application.dtos.responses.UserResponse;
import net.foodeals.user.domain.entities.Authority;
import net.foodeals.user.domain.entities.Role;
import net.foodeals.user.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.context.DelegatingApplicationListener;

@Configuration
@RequiredArgsConstructor
public class UserModelMapperConfig {

    private final ModelMapper modelMapper;
    private final DelegatingApplicationListener delegatingApplicationListener;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.addConverter(context -> {
            final Authority authority = context.getSource();
            return new AuthorityResponse(authority.getId(), authority.getName(), authority.getValue());
        }, Authority.class, AuthorityResponse.class);

        modelMapper.addConverter(context -> {
            final Role role = context.getSource();
            final var authorities = role.getAuthorities()
                    .stream()
                    .map(authority -> modelMapper.map(authority, AuthorityResponse.class))
                    .toList();
            return new RoleResponse(role.getId(), role.getName(), authorities);
        }, Role.class, RoleResponse.class);

        modelMapper.addConverter(context -> {
            final User user = context.getSource();
            final RoleResponse roleResponse = modelMapper.map(user.getRole(), RoleResponse.class);
            System.out.println("user converter is working");
            System.out.println(user.getRole());

            return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone(), roleResponse);
        }, User.class, UserResponse.class);
    }
}
