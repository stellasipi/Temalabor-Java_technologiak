package iwiw.service;


import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

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

        userService.changePassword(user, "oldPassword", "newPassword");

        assertEquals(userRepository.findByName("test").get(0).getPassword(), "newPassword");
    }

    @Test
    public void testChangingUserName(){

        User user = User.builder().userName("test").name("test").password("oldPassword").build();

        when(userRepository.findByName("test")).thenReturn(Arrays.asList(user));

        userService.changeUserName(user, "newUserName");

        assertEquals(userRepository.findByName("test").get(0).getPassword(), "newPassword");

    }
}
