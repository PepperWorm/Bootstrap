package web.springBootSecurityProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.springBootSecurityProject.models.User;
import web.springBootSecurityProject.repositories.UserRepositories;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepositories repositories;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepositories repositories, PasswordEncoder encoder) {
        this.repositories = repositories;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repositories.save(user);
    }

    @Override
    public User getUserById(int id) {
        return repositories.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return repositories.findByUsername(name);
    }

    @Override
    public List<User> getAllUsers() {
        return repositories.findAll();
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        user.setId(id);
        repositories.save(user);
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        repositories.deleteById(id);
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        User user = getUserById(id);
        if (user != null) {
            user.setPassword(newPassword);
            repositories.save(user);
        }
    }

}
