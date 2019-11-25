package iwiw.service;

import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        String subject="Teszt üzi";
        String body="Ez egy teszt üzenet Fromtól Tonak. Hellooo";

        when(userRepository.findById(fromUser.getId())).thenReturn(Optional.of(fromUser));
        when(userRepository.findById(toUser.getId())).thenReturn(Optional.of(toUser));

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

        when(messageRepository.findMessagesByAddressee(testUser1)).thenReturn(Arrays.asList(testMessage1));
        when(messageRepository.findMessagesByAddressee(testUser2)).thenReturn(Arrays.asList(testMessage2));

        //ACT
        List<Message> user1Inbox=messageService.listUsersInbox(testUser1);
        List<Message> user2Inbox=messageService.listUsersInbox(testUser2);

        //ASSERT
        assertEquals(Optional.of(1),Optional.of(user1Inbox.get(0).getId()));
        assertEquals(Optional.of(2),Optional.of(user2Inbox.get(0).getId()));

    }


}
