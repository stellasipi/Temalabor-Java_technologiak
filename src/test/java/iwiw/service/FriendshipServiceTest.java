package iwiw.service;

import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
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
        when(userRepository.findById(testUser2.getId())).thenReturn(Optional.of(testUser2));

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
        assertThat(testUser2.getFriends().size(),equalTo(0));
        assertThat(testUser2.getFriendOf().size(),equalTo(0));

        assertThat(testUser1.getFriends().size(),equalTo(0));
        assertThat(testUser1.getFriendOf().size(),equalTo(0));
    }
}
