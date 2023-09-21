package web.springBootSecurityProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.springBootSecurityProject.models.Role;
import web.springBootSecurityProject.repositories.RoleRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepositories roleRepositories;

    @Autowired
    public RoleServiceImpl(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepositories.findByRoleName(name);
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleRepositories.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepositories.findAll();
    }
}
