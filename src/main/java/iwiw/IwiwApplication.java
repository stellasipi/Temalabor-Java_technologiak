package iwiw;


import iwiw.model.Event;
import iwiw.model.Note;
import iwiw.model.Place;
import iwiw.model.User;
import iwiw.repository.EventRepository;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class IwiwApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    public static void main(String[] args) {
        SpringApplication.run(IwiwApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder().name("teszt1").userName("teszt1").password("teszt1").build();
        User user2 = User.builder().name("teszt2").userName("teszt2").password("teszt2").build();

        user2.addNote(new Note());

        userRepository.save(user2);
        user1.addFriend(user2);
        userRepository.save(user1);

        Place testPlace = Place.builder().name("testPlace").city("testCity").country("testCountry").build();
        Event testEvent = Event.builder().name("testEventName").date(new Date()).build();

        testPlace.addEvent(testEvent);

        eventRepository.save(testEvent);
        user1.addCreatedEvent(testEvent);

        user2.addParticipatedEvent(testEvent);

        userRepository.save(user2);
        userRepository.save(user1);


    }
}