package iwiw;


import iwiw.model.*;
import iwiw.repository.EventRepository;
import iwiw.repository.MessageRepository;
import iwiw.repository.TagRepository;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class IwiwApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TagRepository tagRepository;

    public static void main(String[] args) {
        SpringApplication.run(IwiwApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        User user1 = User.builder().name("Teszt Elek").userName("tesztelek").password("tesztelek").build();
        User user2 = User.builder().name("Mezei Virág").userName("mezeivirag").password("mezeivirag").build();

        user2.addNote(new Note());

        userRepository.save(user2);
        userRepository.save(user1);
        user1.addFriend(user2);

        Place testPlace = Place.builder().name("Deák Ferenc tér").city("Budapest").country("Magyarország").build();
        Event testEvent = Event.builder().name("Szülinapi buli").date(new Date()).build();

        testPlace.addEvent(testEvent);

        eventRepository.save(testEvent);
        user1.addCreatedEvent(testEvent);

        user2.addParticipatedEvent(testEvent);

        userRepository.save(user1);

        Tag testTag = Tag.builder().name("Fontos").build();
        tagRepository.save(testTag);

        Message testMassage = Message.builder().sender(user1).addressee(user2).body("Szia Virág! Pénteken lesz a szülinapi bulim, tudsz jönni? Elek").sentDate(new Date()).subject("Meghívó").build();
        testMassage.addTag(testTag);
        messageRepository.save(testMassage);


    }
}