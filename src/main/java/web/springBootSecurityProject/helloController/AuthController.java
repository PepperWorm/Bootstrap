package web.springBootSecurityProject.helloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.springBootSecurityProject.models.Role;
import web.springBootSecurityProject.models.User;
import web.springBootSecurityProject.repositories.RoleRepositories;
import web.springBootSecurityProject.services.RoleService;
import web.springBootSecurityProject.services.RoleServiceImpl;
import web.springBootSecurityProject.services.UserServiceImpl;
import web.springBootSecurityProject.util.UserValidator;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private UserValidator validator;
    private UserServiceImpl userService;

    private RoleService roleService;
    @Autowired
    public AuthController(UserValidator validator, UserServiceImpl userService, RoleService roleService) {
        this.validator = validator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/home")
    public String homePage() {
        return "auth/home";
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        Role userRole = roleService.findRoleByName("ROLE_USER").orElseGet(() -> {
            Role newUserRole = new Role("ROLE_USER");
            roleService.save(newUserRole);
            return newUserRole;
        });
        user.getRoles().add(userRole);

        userService.register(user);

        return "redirect:/auth/home";
    }
}
