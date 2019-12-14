package iwiw.web;

import iwiw.model.Note;
import iwiw.model.User;
import iwiw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String accountPage(Model model, Principal userPrincipal){

        User user = userService.getById(Integer.parseInt(userPrincipal.getName()));

        model.addAttribute("name", user.getName());

        Set<User> friends = user.getFriends();
        model.addAttribute("friends", friends);

        List<User> allUser = userService.findAll();
        allUser.removeAll(friends);
        allUser.remove(user);

        model.addAttribute("unknowns", allUser);

        model.addAttribute("notes", user.getNotes());

        return "account";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer userId, Principal userPrincipal){

        User user = userService.getById(Integer.parseInt(userPrincipal.getName()));

        userService.removeFriend(user, userService.getById(userId));


        return "redirect:/account/";
    }

    @PostMapping("/addFriend")
    public String addFriend(@RequestParam Integer userId, Principal userPrincipal){

        User user = userService.getById(Integer.parseInt(userPrincipal.getName()));
        userService.addFriend(user, userService.getById(userId));

        return "redirect:/account/";
    }

    @PostMapping("removeNote")
    public String removeNote(@RequestParam Integer noteId, Principal userPrincipal){
        User user = userService.getById(Integer.parseInt(userPrincipal.getName()));
        userService.removeNote(user, noteId);
        return "redirect:/account/";
    }

    @PostMapping("openNote")
    public String openNote(Model model, @RequestParam Integer noteId, Principal userPrincipal){
        User user = userService.getById(Integer.parseInt(userPrincipal.getName()));
        Note note = user.getNotes().stream().filter(n -> n.getId().equals(noteId)).findFirst().get();

        model.addAttribute("title", note.getTitle());
        model.addAttribute("creationTime", note.getCreationTime().toString());
        model.addAttribute("text", note.getText());

        return "note";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest servletRequest){
        servletRequest.removeAttribute("user");
        servletRequest.getSession().removeAttribute("user");
        SecurityContextHolder.clearContext();
        servletRequest.changeSessionId();

        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return "redirect:" + baseUrl + "/login";
    }



}
