package dev.amirgol.springcqrs.infrastructure.repository;

import dev.amirgol.springcqrs.infrastructure.model.CustomerMongoEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerMongoRepository extends MongoRepository<CustomerMongoEntity, UUID> {
    Optional<CustomerMongoEntity> findByFullName(String fullName);

    Optional<CustomerMongoEntity> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(@NotNull String email);

    boolean existsByFullName(@NotNull String fullName);
}
