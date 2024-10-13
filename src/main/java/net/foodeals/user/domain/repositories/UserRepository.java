package net.foodeals.user.domain.repositories;

import net.foodeals.common.contracts.BaseRepository;
import net.foodeals.user.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.role r JOIN FETCH r.authorities WHERE u.email = :email")
    Optional<User> findByEmailWithRoleAndAuthorities(@Param("email") String email);
}
