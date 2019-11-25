package iwiw.service;

import iwiw.model.User;
import iwiw.repository.PlaceRepository;
import iwiw.repository.UserRepository;
import iwiw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

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
}
