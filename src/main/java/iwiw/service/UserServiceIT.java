package iwiw.service;
import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class UserServiceIT {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testChangingPassword(){

        User user = User.builder().userName("test").name("test").password("oldPassword").build();
        userRepository.save(user);

        userService.changePassword(user, "oldPassword", "newPassword");

        user = userRepository.findByName("test").get(0);
        assertEquals(user.getPassword(), "newPassword");
    }

    @Test
    public void testChangingUserName(){

        User user = User.builder().userName("test").name("test").password("oldPassword").build();
        userRepository.save(user);

        userService.changeUserName(user, "changedUserName");

        user = userRepository.findByName("test").get(0);
        assertEquals(user.getUserName(), "changedUserName");
    }
}
