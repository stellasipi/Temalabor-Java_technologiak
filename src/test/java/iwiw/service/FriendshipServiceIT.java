package iwiw.service;

import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class FriendshipServiceIT {
    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateNewFriendshipBetween() throws Exception{
        //ARRANGE
        User testUser1=User.builder()
                /*.id(1)*/
                .userName("testUser1")
                .name("Test User 1")
                .build();
        User testUser2=User.builder()
                /*.id(2)*/
                .userName("testUser2")
                .name("Test User 2")
                .build();


        userRepository.save(testUser1);
        userRepository.save(testUser2);

        //ACT
        friendshipService.createNewFriendshipBetween(testUser1,testUser2);

        //ASSERT
        assertThat(testUser2.getFriends().iterator().next(),equalTo(testUser1));
        assertThat(testUser2.getFriendOf().iterator().next(),equalTo(testUser1));

        assertThat(testUser1.getFriends().iterator().next(),equalTo(testUser2));
        assertThat(testUser1.getFriendOf().iterator().next(),equalTo(testUser2));
    }

    @Test
    public void testDeleteFriendshipBetween() throws Exception{
        //ARRANGE
        User testUser1=User.builder()
                //.id(1)
                .userName("testUser1")
                .name("Test User 1")
                .build();
        User testUser2=User.builder()
                //.id(2)
                .userName("testUser2")
                .name("Test User 2")
                .build();
        testUser1.addFriend(testUser2);

        userRepository.save(testUser1);
        userRepository.save(testUser2);

        //ACT
        friendshipService.deleteFriendshipBetween(testUser1,testUser2);

        //ASSERT

        assertThat(testUser2.getFriends().size(),equalTo(0));
        assertThat(testUser2.getFriendOf().size(),equalTo(0));

        assertThat(testUser1.getFriends().size(),equalTo(0));
        assertThat(testUser1.getFriendOf().size(),equalTo(0));
    }
}
