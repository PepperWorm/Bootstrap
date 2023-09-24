package web.springBootSecurityProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.springBootSecurityProject.services.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImpl service;


    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("usingUser", service.getUserByName(principal.getName()));
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
