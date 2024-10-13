package net.foodeals.user.application.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.foodeals.user.application.dtos.requests.UserRequest;
import net.foodeals.user.application.services.RoleService;
import net.foodeals.user.application.services.UserService;
import net.foodeals.user.domain.entities.Role;
import net.foodeals.user.domain.entities.User;
import net.foodeals.user.domain.exceptions.UserNotFoundException;
import net.foodeals.user.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<User> findAll(Integer pageNumber, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User create(UserRequest request) {
        final User user = mapRelationsAndEncodePassword(
                modelMapper.map(request, User.class),
                request
        );
        return repository.save(user);
    }

    @Override
    public User update(Integer id, UserRequest request) {
        final User existingUser = findById(id);
        modelMapper.map(request, existingUser);
        final User user = mapRelationsAndEncodePassword(
                existingUser, request
        );
        return repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id))
            throw new UserNotFoundException(id);
        repository.softDelete(id);
    }

    private User mapRelationsAndEncodePassword(User user, UserRequest request) {
        final Role role = roleService.findById(request.roleId());
        user.setRole(role)
                .setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
