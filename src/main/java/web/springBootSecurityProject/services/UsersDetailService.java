package web.springBootSecurityProject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.springBootSecurityProject.models.User;
import web.springBootSecurityProject.repositories.UserRepositories;

import java.util.Optional;

@Service
public class UsersDetailService implements UserDetailsService {
    private final UserRepositories repositories;

    public UsersDetailService(UserRepositories repositories) {
        this.repositories = repositories;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repositories.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}
