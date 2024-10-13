package net.foodeals.organizationEntity.infrastructure.seeders;

import lombok.RequiredArgsConstructor;
import net.foodeals.common.annotations.Seeder;
import net.foodeals.organizationEntity.domain.entities.Activity;
import net.foodeals.organizationEntity.domain.repositories.ActivityRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Seeder
@RequiredArgsConstructor
public class ActivitySeeder implements CommandLineRunner {

    private final ActivityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                    Activity.create("Activity 1"),
                    Activity.create("Activity 2"),
                    Activity.create("Activity 3"),
                    Activity.create("Activity 4")
            ));
        }
    }
}
