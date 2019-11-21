package iwiw.service;

import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaggingServiceTest {

    @InjectMocks
    TaggingService taggingService;

    @Mock
    MessageRepository messageRepository;

    @Test
    public void testTaggingMessage() {
        User user = User.builder().name("testUser").build();
        Message message = Message.builder().addressee(user).build();

        Tag tag = Tag.builder().name("fontos").build();

        when(messageRepository.findAllMessagesByAddresseeAndTagsIs(user, tag)).thenReturn((ArrayList<Message>) Arrays.asList(message));

        taggingService.addTagToMessage(tag, message);

        assertEquals(messageRepository.findAllMessagesByAddresseeAndTagsIs(user, tag).get(0).getTags().contains(tag), true);
    }
}
