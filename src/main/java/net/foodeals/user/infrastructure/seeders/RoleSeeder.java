package net.foodeals.user.infrastructure.seeders;

import lombok.RequiredArgsConstructor;
import net.foodeals.common.annotations.Seeder;
import net.foodeals.user.domain.entities.Authority;
import net.foodeals.user.domain.entities.Role;
import net.foodeals.user.domain.repositories.AuthorityRepository;
import net.foodeals.user.domain.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.UUID;

@Seeder
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            List<Authority> authorities = authorityRepository.findAll();
            roleRepository.saveAll(
                    List.of(
                            Role.create(UUID.randomUUID(), "ADMIN", authorities),
                            Role.create(UUID.randomUUID(), "SUPER_ADMIN", authorities),
                            Role.create(UUID.randomUUID(), "ASSOCIATION", authorities),
                            Role.create(UUID.randomUUID(), "PARTNER", authorities),
                            Role.create(UUID.randomUUID(), "SALES_MANAGER", authorities),
                            Role.create(UUID.randomUUID(), "USER", authorities),
                            Role.create(UUID.randomUUID(), "CLIENT", authorities)
                    )
            );
            System.out.println("roles seeded");
        }
    }
}
