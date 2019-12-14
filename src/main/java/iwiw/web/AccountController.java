package iwiw.web;

import iwiw.dto.MessageCreationDto;
import iwiw.model.Message;
import iwiw.model.Note;
import iwiw.dto.NoteCreationDto;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.service.MessageService;
import iwiw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/")
    public String accountPage(Model model, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        model.addAttribute("name", user.getName());
        Set<User> friends = user.getFriends();
        model.addAttribute("friends", friends);
        List<User> allUser = userService.findAll();
        allUser.removeAll(friends);
        allUser.remove(user);
        model.addAttribute("unknowns", allUser);
        model.addAttribute("notes", user.getNotes());
        model.addAttribute("messages", user.getReceivedMessages());
        return "account";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer userId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        userService.removeFriend(user, userService.findById(userId));
        return "redirect:/account/";
    }

    @PostMapping("/addFriend")
    public String addFriend(@RequestParam Integer userId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        userService.addFriend(user, userService.findById(userId));
        return "redirect:/account/";
    }

    @PostMapping("removeNote")
    public String removeNote(@RequestParam Integer noteId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        userService.removeNote(user, noteId);
        return "redirect:/account/";
    }

    @PostMapping("openNote")
    public String openNote(Model model, @RequestParam Integer noteId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Note note = user.getNotes().stream().filter(n -> n.getId().equals(noteId)).findFirst().get();
        model.addAttribute("title", note.getTitle());
        model.addAttribute("creationTime", note.getCreationTime().toString());
        model.addAttribute("text", note.getText());
        return "note";
    }

    @PostMapping("openMessage")
    public String openMessage(Model model, @RequestParam Integer messageId, Principal userPrincipal){
        //User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Message message = messageRepository.findById(messageId).get();
        model.addAttribute("subject", message.getSubject());
        model.addAttribute("sender", message.getSender().toString());
        model.addAttribute("sentDate", message.getSentDate().toString());
        model.addAttribute("text", message.getBody());
        return "message";
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

    @GetMapping("showCreateNotePage")
    public String showCreateNotePage(Model model){
      model.addAttribute("note", new NoteCreationDto());
      return "createNote";
    }

    @PostMapping("/createNote")
    public String createNote(@ModelAttribute NoteCreationDto noteCreationDto, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Note note = new Note(user, noteCreationDto.getText(), new Date(), noteCreationDto.getTitle());
        userService.addNote(user, note);
        return "redirect:/account/";
    }

    @GetMapping("createNewMessage")
    public String createMessage(Model model){
        model.addAttribute("message", new MessageCreationDto());
        return "createNewMessage";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute MessageCreationDto messageCreationDto, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        User addressee = userService.findById(Integer.parseInt(messageCreationDto.getAddressee()));
        messageService.sendMessage(user, addressee, messageCreationDto.getSubject(), messageCreationDto.getText());
        return "redirect:/account/";
    }

}
