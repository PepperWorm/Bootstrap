package web.springBootSecurityProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.springBootSecurityProject.models.User;

import java.util.List;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles")
    List<User> getAllUsers();

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles where u.email = :email")
    User findByEmail(@Param("email") String email);
}
