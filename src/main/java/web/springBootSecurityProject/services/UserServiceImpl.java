package web.springBootSecurityProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.springBootSecurityProject.models.User;
import web.springBootSecurityProject.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepositories repositories;
    @Autowired
    public UserServiceImpl(UserRepositories repositories) {
        this.repositories = repositories;
    }

    @Transactional
    @Override
    public void register(User user) {
        repositories.save(user);
    }

    @Override
    public User getUserById(int id) {
        return repositories.findById(id).orElse(null);
    }

    @Override
    public Optional<User> getUserByName(String name) {;
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
