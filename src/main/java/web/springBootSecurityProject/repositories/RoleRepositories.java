package web.springBootSecurityProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.springBootSecurityProject.models.Role;
import web.springBootSecurityProject.models.User;

import java.util.Optional;

@Repository
public interface RoleRepositories extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String name);
}
