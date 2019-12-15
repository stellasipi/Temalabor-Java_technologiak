package iwiw.web;

import iwiw.dto.EventCreationDto;
import iwiw.model.*;
import iwiw.repository.EventRepository;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import iwiw.service.EventService;
import iwiw.service.MessageService;
import iwiw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @PostMapping("openCreatedEvent")
    public String openCreatedEvent(Model model, @RequestParam Integer eventId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Event event = user.getCreatedEvents().stream().filter(n -> n.getId().equals(eventId)).findFirst().get();
        model.addAttribute("name", event.getName());
        model.addAttribute("date", event.getDate().toString());
        model.addAttribute("location", event.getPlace().toString());
        return "createdEvent";
    }

    @GetMapping("showCreatedEventPage")
    public String showCreatedEventPage(Model model){
        model.addAttribute("event",new EventCreationDto());
        return "createEvent";
    }

    /*@PostMapping("openInvitesEvent")
    public String openInvitedEvent(Model model, @RequestParam Integer eventId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Event event = user.getParticipatedEvents().stream().filter(n -> n.getId().equals(eventId)).findFirst().get();
        model.addAttribute("name", event.getName());
        model.addAttribute("date", event.getDate().toString());
        model.addAttribute("location", event.getPlace().toString());
        return "invitedEvent";
    }*/


    //eseménylétrehozás
    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventCreationDto eventCreationDto, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Event event = new Event(user, eventCreationDto.getName(), new Date(), new Place(eventCreationDto.getName(),eventCreationDto.getPlace_city(),eventCreationDto.getPlace_country()));
        user.addCreatedEvent(event);
        return "redirect:/account/";
    }

    //meghívni barátokat
    @PostMapping("/{id}/inviteFriends")
    public void inviteFriends(){

    }

    /*//kilistázni az létrehozott eseményeket
    @GetMapping("/createdEvents/{userId}")
    public void getCreatedEvents(@PathVariable Integer userId){

    }

    //kilistázni az eseményeket, amire meghívtak
    @GetMapping("/invitedEvents/{userId}")
    public void getInvitedEvents(@PathVariable Integer userId){

    }*/

    //kilistázni az esemény résztvevőit
    @GetMapping("/{id}/participants")
    public void getParticipants(){

    }

    //értékelést kérni
    @PostMapping("/{id}/requestEvaliation")
    public void requestEvaluation(){

    }

    //esemény törlése
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id){
        eventRepository.deleteById(id);
    }

    //esemény módosítása ???
    @PutMapping("/{id}/modifyEvent")
    public void modifyEvent(){

    }

}
