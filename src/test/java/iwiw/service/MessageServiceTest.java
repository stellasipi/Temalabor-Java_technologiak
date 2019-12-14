package iwiw.service;

import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import iwiw.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @InjectMocks
    MessageService messageService;

    @Mock
    UserRepository userRepository;

    @Mock
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
        /*String subject="Teszt üzi";
        String body="Ez egy teszt üzenet Fromtól Tonak. Hellooo";*/
        Message message=Message.builder()
                .id(1)
                .subject("Teszt üzi")
                .body("Ez egy teszt üzenet Fromtól Tonak. Hellooo")
                .build();

        when(userRepository.findById(fromUser.getId())).thenReturn(Optional.of(fromUser));
        when(userRepository.findById(toUser.getId())).thenReturn(Optional.of(toUser));

        //ACT
        messageService.sendMessage(fromUser,toUser,message);

        //ASSERT
        assertThat(fromUser.getSentMessages().iterator().next().getSubject(),equalTo(message.getSubject()));
        assertThat(fromUser.getSentMessages().iterator().next().getBody(),equalTo(message.getBody()));
        assertThat(toUser.getReceivedMessages().iterator().next().getSubject(),equalTo(message.getSubject()));
        assertThat(toUser.getReceivedMessages().iterator().next().getBody(),equalTo(message.getBody()));

    }

    @Test
    public void testListUsersInbox(){
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

        when(messageRepository.findMessagesByAddressee(testUser1)).thenReturn(Arrays.asList(testMessage1));
        when(messageRepository.findMessagesByAddressee(testUser2)).thenReturn(Arrays.asList(testMessage2));

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
        User userSender = User.builder().userName("sender").name("sender").id(1).build();
        User user1 = User.builder().userName("user1").name("user1").id(2).build();
        User user2 = User.builder().userName("user2").name("user2").id(3).build();
        Message message = Message.builder().subject("Körlevél").body("Hello hello sziasztok!").build();

        userSender.addFriend(user1);
        userSender.addFriend(user2);

        when(userRepository.findById(1)).thenReturn(Optional.of(userSender));
        when(userRepository.findById(2)).thenReturn(Optional.of(user1));
        when(userRepository.findById(3)).thenReturn(Optional.of(user2));


        //ACT
        messageService.sendMessageToAllFriends(userSender, message);

        //ASSERT
        assertThat(user1.getReceivedMessages().size(), equalTo(1));
        assertThat(user2.getReceivedMessages().size(), equalTo(1));
        assertThat(userRepository.findById(2).get().getReceivedMessages().contains(message), is(true));
        assertThat(userRepository.findById(3).get().getReceivedMessages().contains(message), is(true));

    }


}
