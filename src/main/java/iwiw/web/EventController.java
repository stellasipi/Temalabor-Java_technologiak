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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("showCreateEventPage")
    public String showCreatedEventPage(Model model){
        model.addAttribute("event",new EventCreationDto());
        return "createEvent";
    }

    @PostMapping("openInvitesEvent")
    public String openInvitedEvent(Model model, @RequestParam Integer eventId, Principal userPrincipal){
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        Event event=userService.getParticipatedEvents(user).stream().filter(n -> n.getId().equals(eventId)).findFirst().get();
        model.addAttribute("name", event.getName());
        model.addAttribute("date", event.getDate().toString());
        model.addAttribute("place_name", event.getPlace().getName());
        model.addAttribute("place_city", event.getPlace().getCity());
        model.addAttribute("place_country", event.getPlace().getCountry());
        return "invitedEvent";
    }


    //eseménylétrehozás
    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventCreationDto eventCreationDto, Principal userPrincipal) throws ParseException {
        User user = userService.findById(Integer.parseInt(userPrincipal.getName()));
        DateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
        Date date=formatter.parse(eventCreationDto.getDate());
        Event event = new Event(user, eventCreationDto.getName(), date, new Place(eventCreationDto.getPlace_name(),
                eventCreationDto.getPlace_city(),eventCreationDto.getPlace_country()));
        userService.createEvent(user,event);
        return "redirect:/account/";
    }

    //esemény törlése
    @PostMapping("/delete")
    public String deleteEvent(@RequestParam Integer eventId){
        Event event=eventRepository.findById(eventId).get();
        eventService.deleteEvent(event);
        return "redirect:/account/";
    }
}
