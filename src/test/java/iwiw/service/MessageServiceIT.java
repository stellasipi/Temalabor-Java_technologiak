package iwiw.service;

import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class MessageServiceIT {

    @Autowired
    MessageService messageService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void testSendMessage() throws Exception{
        //ARRANGE
        User fromUser=User.builder()
                .id(1)
                .userName("fromUser")
                .name("From User")
                .build();
        User toUser=User.builder()
                .id(2)
                .userName("toUser")
                .name("To User")
                .build();
        String subject="Teszt üzi";
        String body="Ez egy teszt üzenet Fromtól Tonak. Hellooo";

        userRepository.save(fromUser);
        userRepository.save(toUser);

        //ACT
        messageService.sendMessage(fromUser,toUser,subject,body);

        //ASSERT
        assertEquals(subject,fromUser.getSentMessages().iterator().next().getSubject());
        assertEquals(subject,toUser.getReceivedMessages().iterator().next().getSubject());
    }

    @Test
    public void testListUsersInbox() throws Exception{
        //ARRANGE
        User testUser1=User.builder()
                .id(1)
                .userName("test_username1")
                .name("Test User1")
                .build();
        User testUser2=User.builder()
                .id(2)
                .userName("test_username2")
                .name("Test User2")
                .build();
        Message testMessage1=Message.builder()
                .id(1)
                .addressee(testUser1)
                .sender(testUser2)
                .build();
        Message testMessage2=Message.builder()
                .id(2)
                .addressee(testUser2)
                .sender(testUser1)
                .build();

        testUser1.addIncomingMessage(testMessage1);
        testUser1.addOutgoingMessage(testMessage2);

        testUser2.addOutgoingMessage(testMessage1);
        testUser2.addIncomingMessage(testMessage2);

        messageRepository.save(testMessage1);
        messageRepository.save(testMessage2);

        //ACT
        List<Message> user1Inbox=messageService.listUsersInbox(testUser1);
        List<Message> user2Inbox=messageService.listUsersInbox(testUser2);

        //ASSERT
        assertEquals(Optional.of(1),Optional.of(user1Inbox.get(0).getId()));
        assertEquals(Optional.of(2),Optional.of(user2Inbox.get(0).getId()));

    }
}
