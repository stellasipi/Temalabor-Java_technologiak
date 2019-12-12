package iwiw.web;

import iwiw.model.User;
import iwiw.model.UserCreationDto;
import iwiw.service.SecurityService;
import iwiw.service.UserService;
import iwiw.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String registration(Map<String, Object> model) {


        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String userName,
            @RequestParam String password,
            Model model,
            HttpServletRequest request){



        return "login";
    }




    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new UserCreationDto());
        return "register";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute UserCreationDto user, Model model){

        userService.createUser(user);
        return "redirect:/login";

    }

}
