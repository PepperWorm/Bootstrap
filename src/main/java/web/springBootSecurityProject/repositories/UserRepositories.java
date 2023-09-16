package web.springBootSecurityProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.springBootSecurityProject.models.User;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
