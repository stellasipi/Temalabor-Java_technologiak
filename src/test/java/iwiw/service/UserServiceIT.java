package iwiw.service;

import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.PlaceRepository;
import iwiw.repository.TagRepository;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class UserServiceIT {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    MessageRepository messageRepository;


    @Test
    public void testChangingPassword(){

        User user = new User(1, "test", "test", "oldPassword");
        user = userRepository.save(user);

        userService.changePassword(user, "oldPassword", "newPassword");

        user = userRepository.findByName("test").get(0);
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testChangingUserName(){

        User user = new User(2, "test", "test", "oldPassword");
        user = userRepository.save(user);

        userService.changeUserName(user, "changedUserName");

        user = userRepository.findByName("test").get(0);
        assertEquals("changedUserName", user.getUserName());
    }

    @Test
    public void testDeleteMessagesWithASpecificTag(){
        //ARRANGE
        User user= User.builder()
                .name("Teszt Bence")
                .userName("tesztbence")
                .password("tesztbence")
                //.id(1)
                .build();
        User userAdditional=User.builder()
                .name("Teszt Dóra")
                .userName("tesztdora")
                .password("tesztdora")
                //.id(2)
                .build();
        Tag tagImportant = Tag.builder()
                .name("Fontos")
                //.id(1)
                .build();
        Tag tagSpam = Tag.builder()
                .name("Spam")
                //.id(2)
                .build();
        Message message1=Message.builder()
                .sender(userAdditional)
                .addressee(user)
                .subject("Subject1")
                .body("Body1")
                .sentDate(new Date(System.currentTimeMillis()))
                .build();
        Message message2=Message.builder()
                .sender(userAdditional)
                .addressee(user)
                .subject("Subject2")
                .body("Body2")
                .sentDate(new Date(System.currentTimeMillis()))
                .build();
        Message message3=Message.builder()
                .sender(userAdditional)
                .addressee(user)
                .subject("Subject3")
                .body("Body3")
                .sentDate(new Date(System.currentTimeMillis()))
                .build();
        userAdditional.addOutgoingMessage(message1);
        userAdditional.addOutgoingMessage(message2);
        userAdditional.addOutgoingMessage(message3);
        user.addIncomingMessage(message1);
        user.addIncomingMessage(message2);
        user.addIncomingMessage(message3);
        message1.addTag(tagImportant);
        message2.addTag(tagSpam);
        message3.addTag(tagSpam);

        userRepository.save(user);
        userRepository.save(userAdditional);
        tagRepository.save(tagImportant);
        tagRepository.save(tagSpam);
        messageRepository.save(message1);
        messageRepository.save(message2);
        messageRepository.save(message3);


        //ACT
        userService.deleteMessagesWithASpecificTag(user,tagSpam);

        //ASSERT
        assertThat(userRepository.findById(user.getId()).get().getReceivedMessages().size(),equalTo(1));
        assertThat(userRepository.findById(user.getId()).get().getReceivedMessages().iterator().next().getTags().iterator().next(),equalTo(tagImportant));

    }
}
