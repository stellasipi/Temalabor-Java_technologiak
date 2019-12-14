package iwiw.service;


import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.UserRepository;
import iwiw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testChangingPassword(){

        User user = User.builder().userName("test").name("test").password("oldPassword").build();

        when(userRepository.findByName("test")).thenReturn(Arrays.asList(user));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.changePassword(user, "oldPassword", "newPassword");

        assertEquals("newPassword", userRepository.findByName("test").get(0).getPassword());
    }

    @Test
    public void testChangingUserName(){

        User user = User.builder().userName("test").name("test").password("oldPassword").build();

        when(userRepository.findByName("test")).thenReturn(Arrays.asList(user));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.changeUserName(user, "newUserName");

        assertEquals("newUserName", userRepository.findByName("test").get(0).getUserName());

    }

    @Test
    public void testDeleteMessagesWithASpecificTag(){
        //ARRANGE
        User user= User.builder()
                .name("Teszt Bence")
                .userName("tesztbence")
                .password("tesztbence")
                .id(1)
                .build();
        User userAdditional=User.builder()
                .name("Teszt Dóra")
                .userName("tesztdora")
                .password("tesztdora")
                .id(2)
                .build();
        Tag tagImportant = Tag.builder()
                .name("Fontos")
                .id(1)
                .build();
        Tag tagSpam = Tag.builder()
                .name("Spam")
                .id(2)
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

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findById(2)).thenReturn(Optional.of(userAdditional));

        //ACT
        userService.deleteMessagesWithASpecificTag(user,tagSpam);

        //ASSERT
        assertThat(userRepository.findById(1).get().getReceivedMessages().size(),equalTo(1));
        assertThat(userRepository.findById(1).get().getReceivedMessages().iterator().next().getTags().iterator().next(),equalTo(tagImportant));

    }
}
