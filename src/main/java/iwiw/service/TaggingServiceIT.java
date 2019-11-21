package iwiw.service;

import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.TagRepository;
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
public class TaggingServiceIT {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TaggingService taggingService;

    @Test
    public void testingTaggingMessage(){

        //Teszt User, és Message létrehozása
        User user = User.builder().name("testUser").build();
        Message message = Message.builder().addressee(user).build();
        messageRepository.save(message);

        Tag tag = Tag.builder().name("fontos").build();
        taggingService.addTagToMessage(tag, message);

        message = messageRepository.findAllMessagesByAddresseeAndTagsIs(user, tag).get(0);

        assertEquals(message.getTags().contains(tag), true);
    }
}
