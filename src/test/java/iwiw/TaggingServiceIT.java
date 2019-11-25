package iwiw;

import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.TagRepository;
import iwiw.repository.UserRepository;
import iwiw.service.TaggingService;
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
public class TaggingServiceIT {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TaggingService taggingService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testingTaggingMessage(){
        User sender = User.builder().id(2).name("teszt2").userName("teszt2").password("teszt2").build();
        sender = userRepository.save(sender);

        User user = new User(1, "test", "test", "oldPassword");
        user = userRepository.save(user);
        Message message = Message.builder().addressee(user).id(1).sender(sender).body(" ").subject(" ").build();
        message = messageRepository.save(message);

        Tag tag = new Tag(1,"fontos");
        tag = tagRepository.save(tag);
        taggingService.addTagToMessage(tag, message);

        assertEquals(tag,tagRepository.findById(1).get());
        message = messageRepository.findAllMessagesByAddresseeAndTagsIs(user, tag).get(0);
        assertEquals(message.getTags().contains(tag), true);
    }
}
