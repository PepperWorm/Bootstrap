package web.springBootSecurityProject.helloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.springBootSecurityProject.services.RoleService;
import web.springBootSecurityProject.services.UserService;
import web.springBootSecurityProject.util.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private RoleService roleService;
    private UserService userService;
    private UserValidator validator;

    @Autowired
    public AdminController(RoleService roleService, UserService userService, UserValidator validator) {
        this.roleService = roleService;
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/admin";
    }
}
