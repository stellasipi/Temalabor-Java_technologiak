package iwiw.service;

import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipServiceTest {
    @InjectMocks
    FriendshipService friendshipService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testCreateNewFriendshipBetween() throws Exception{
        //ARRANGE
        User testUser1=User.builder()
                .id(1)
                .userName("testUser1")
                .name("Test User 1")
                .build();
        User testUser2=User.builder()
                .id(2)
                .userName("testUser2")
                .name("Test User 2")
                .build();

        when(userRepository.findById(testUser1.getId())).thenReturn(Optional.of(testUser1));

        //ACT
        friendshipService.createNewFriendshipBetween(testUser1,testUser2);

        //ASSERT
        assertEquals(testUser1.getUserName(),testUser2.getFriends().iterator().next().getUserName());
        assertEquals(testUser1.getUserName(),testUser2.getFriendOf().iterator().next().getUserName());

        assertEquals(testUser2.getUserName(),testUser1.getFriends().iterator().next().getUserName());
        assertEquals(testUser2.getUserName(),testUser1.getFriendOf().iterator().next().getUserName());
    }

    @Test
    public void testDeleteFriendshipBetween() throws Exception{
        //ARRANGE
        User testUser1=User.builder()
                .id(1)
                .userName("testUser1")
                .name("Test User 1")
                .build();
        User testUser2=User.builder()
                .id(2)
                .userName("testUser2")
                .name("Test User 2")
                .build();
        testUser1.addFriend(testUser2);
        when(userRepository.findById(testUser1.getId())).thenReturn(Optional.of(testUser1));

        //ACT
        friendshipService.deleteFriendshipBetween(testUser1,testUser2);

        //ASSERT

        assertEquals(0,testUser2.getFriends().size());
        assertEquals(0,testUser2.getFriendOf().size());

        assertEquals(0,testUser1.getFriends().size());
        assertEquals(0,testUser1.getFriendOf().size());
    }
}
