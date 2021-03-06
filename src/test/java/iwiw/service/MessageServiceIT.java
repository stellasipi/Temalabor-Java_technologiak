package iwiw.service;

import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import iwiw.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
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
                /*.id(1)*/
                .userName("fromUser")
                .name("From User")
                .build();
        User toUser=User.builder()
                /*.id(2)*/
                .userName("toUser")
                .name("To User")
                .build();
        /*String subject="Teszt üzi";
        String body="Ez egy teszt üzenet Fromtól Tonak. Hellooo";*/
        Message message=Message.builder()
                .id(1)
                .subject("Teszt üzi")
                .body("Ez egy teszt üzenet Fromtól Tonak. Hellooo")
                .build();

        userRepository.save(fromUser);
        userRepository.save(toUser);

        //ACT
        messageService.sendMessage(fromUser,toUser,message);

        //ASSERT
        assertThat(fromUser.getSentMessages().iterator().next().getSubject(),equalTo(message.getSubject()));
        assertThat(fromUser.getSentMessages().iterator().next().getBody(),equalTo(message.getBody()));
        assertThat(toUser.getReceivedMessages().iterator().next().getSubject(),equalTo(message.getSubject()));
        assertThat(toUser.getReceivedMessages().iterator().next().getBody(),equalTo(message.getBody()));
    }

    @Test
    public void testListUsersInbox() throws Exception{
        //ARRANGE
        User testUser1=User.builder()
                /*.id(1)*/
                .userName("test_username1")
                .name("Test User1")
                .build();
        User testUser2=User.builder()
                /*.id(2)*/
                .userName("test_username2")
                .name("Test User2")
                .build();
        Message testMessage1=Message.builder()
                /*.id(1)*/
                .subject("testMessageSubject1")
                .addressee(testUser1)
                .sender(testUser2)
                .build();
        Message testMessage2=Message.builder()
                /*.id(2)*/
                .subject("testMessageSubject2")
                .addressee(testUser2)
                .sender(testUser1)
                .build();

        testUser1.addIncomingMessage(testMessage1);
        testUser1.addOutgoingMessage(testMessage2);

        testUser2.addOutgoingMessage(testMessage1);
        testUser2.addIncomingMessage(testMessage2);

        userRepository.save(testUser1);
        userRepository.save(testUser2);

        messageRepository.save(testMessage1);
        messageRepository.save(testMessage2);

        //ACT
        List<Message> user1Inbox=messageService.listUsersInbox(testUser1);
        List<Message> user2Inbox=messageService.listUsersInbox(testUser2);

        //ASSERT
        assertThat(user1Inbox.get(0),equalTo(testMessage1));
        assertThat(user2Inbox.get(0),equalTo(testMessage2));

    }

    @Test
    public void sendMessageToAllFriends(){

        //ARRANGE
        User userSender = User.builder().userName("sender").name("sender").build();
        User user1 = User.builder().userName("user1").name("user1").build();
        User user2 = User.builder().userName("user2").name("user2").build();
        Message message = Message.builder().subject("Körlevél").body("Hello hello sziasztok!").build();

        userSender.addFriend(user1);
        userSender.addFriend(user2);

        userRepository.save(userSender);
        userRepository.save(user1);
        userRepository.save(user2);
        messageRepository.save(message);


        //ACT
        messageService.sendMessageToAllFriends(userSender, message);

        //ASSERT
        assertThat(userRepository.findById(user1.getId()).get().getReceivedMessages().size(), equalTo(1));
        assertThat(userRepository.findById(user2.getId()).get().getReceivedMessages().size(), equalTo(1));

        Iterator<Message> iterator = userRepository.findById(user1.getId()).get().getReceivedMessages().iterator();
        Message user1_message = iterator.next();
        assertThat(user1_message.getSubject().equals("Körlevél"), is(true));
        assertThat(user1_message.getBody().equals("Hello hello sziasztok!"), is(true));

        iterator = userRepository.findById(user2.getId()).get().getReceivedMessages().iterator();
        Message user2_message = iterator.next();
        assertThat(user2_message.getSubject().equals("Körlevél"), is(true));
        assertThat(user2_message.getBody().equals("Hello hello sziasztok!"), is(true));

    }
}
