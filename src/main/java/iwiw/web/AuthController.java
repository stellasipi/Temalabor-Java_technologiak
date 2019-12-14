package iwiw.web;

import iwiw.model.User;
import iwiw.model.UserCreationDto;
import iwiw.model.UserPrincipal;
import iwiw.service.SecurityService;
import iwiw.service.UserService;
import iwiw.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
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

        model.addAttribute("userName", userName);

        if(userService.validateAccountLogin(userName,password)){
            Authentication auth = null;
            try{
                User user = userService.getByUserName(userName);
                UserPrincipal userPrincipal = new UserPrincipal(user.getId());
                List<GrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority("ROLE_SimpleRole"));
                auth = new UsernamePasswordAuthenticationToken(userPrincipal, user.getUserName(), roles);
                request.getSession().setAttribute("user", user);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            catch (Exception e){
                if (auth != null)
                    auth.setAuthenticated(false);

                return "login";
            }

            return "redirect:/account/";
        }

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
