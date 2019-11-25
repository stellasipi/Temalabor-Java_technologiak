package iwiw;


import iwiw.model.User;
import iwiw.repository.UserRepository;
import iwiw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
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
}
