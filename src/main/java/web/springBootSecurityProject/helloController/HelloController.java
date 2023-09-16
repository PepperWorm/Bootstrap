package web.springBootSecurityProject.helloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.springBootSecurityProject.models.Role;
import web.springBootSecurityProject.models.User;
import web.springBootSecurityProject.services.UserService;
import web.springBootSecurityProject.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/index")
public class HelloController {

    private UserServiceImpl service;

    @Autowired
    public HelloController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String helloPage(Model model, Principal principal) {
        String userName = principal.getName();
        User user = service.getUserByName(userName).orElse(null);
        if (user != null) {
            model.addAttribute("id", user.getId());
            model.addAttribute("name", user.getUsername());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("age", user.getAge());
            model.addAttribute("role", user.getRoles());
        }
        return "index";
    }
}
