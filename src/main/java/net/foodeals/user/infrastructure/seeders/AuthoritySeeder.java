package net.foodeals.user.infrastructure.seeders;

import lombok.RequiredArgsConstructor;
import net.foodeals.common.annotations.Seeder;
import net.foodeals.user.domain.entities.Authority;
import net.foodeals.user.domain.repositories.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Seeder
@RequiredArgsConstructor
public class AuthoritySeeder implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {
        if (authorityRepository.count() == 0) {
            authorityRepository.saveAll(
                    List.of(
                            Authority.create("READ_USER", "read:user"),
                            Authority.create("WRITE_USER", "write:user"),
                            Authority.create("DELETE_USER", "delete:user"),
                            Authority.create("READ_ROLE", "read:role"),
                            Authority.create("WRITE_ROLE", "write:role"),
                            Authority.create("DELETE_ROLE", "delete:role"),
                            Authority.create("READ_PERMISSION", "read:permission"),
                            Authority.create("WRITE_PERMISSION", "write:permission"),
                            Authority.create("DELETE_PERMISSION", "delete:permission")
                    )
            );
        }
    }
}
