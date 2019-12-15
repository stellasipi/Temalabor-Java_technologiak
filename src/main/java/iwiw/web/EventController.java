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
        model.addAttribute("place_name",event.getPlace().getName());
        model.addAttribute("place_city", event.getPlace().getCity());
        model.addAttribute("place_country", event.getPlace().getCountry());
        return "createdEvent";
    }

    @GetMapping("showCreatEventPage")
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
        model.addAttribute("place_name",event.getPlace().getName());
        model.addAttribute("place_city", event.getPlace().getCity());
        model.addAttribute("place_country", event.getPlace().getCountry());
        return "invitesEvent";
    }*/


    //eseménylétrehozás
    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventCreationDto eventCreationDto, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Event event = new Event(user, eventCreationDto.getName(), new Date(), new Place(eventCreationDto.getPlace_name(),eventCreationDto.getPlace_city(),eventCreationDto.getPlace_country()));
        userService.createEvent(user,event);
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
    public String deleteEvent(@PathVariable Integer id){
        Event event=eventRepository.findById(id).get();
        eventService.deleteEvent(event);
        return "redirect:/account/";
    }

    //esemény módosítása ???
    @PutMapping("/{id}/modifyEvent")
    public void modifyEvent(){

    }

}
