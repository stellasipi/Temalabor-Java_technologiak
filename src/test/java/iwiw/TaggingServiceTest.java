package iwiw;

import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.TagRepository;
import iwiw.service.TaggingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaggingServiceTest {

    @InjectMocks
    TaggingService taggingService;

    @Mock
    MessageRepository messageRepository;

    @Mock
    TagRepository tagRepository;

    @Test
    public void testTaggingMessage() {
        User user = User.builder().name("testUser").build();
        Message message = Message.builder().addressee(user).build();

        Tag tag = Tag.builder().name("fontos").build();

        when(messageRepository.findAllMessagesByAddresseeAndTagsIs(user, tag)).thenReturn(new ArrayList<Message>(Arrays.asList(message)));
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));

        taggingService.addTagToMessage(tag, message);

        assertEquals(true, messageRepository.findAllMessagesByAddresseeAndTagsIs(user, tag).get(0).getTags().contains(tag));

    }
}
