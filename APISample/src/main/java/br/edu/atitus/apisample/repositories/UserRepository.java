package br.edu.atitus.apisample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.atitus.apisample.entities.UserEntity;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
}
