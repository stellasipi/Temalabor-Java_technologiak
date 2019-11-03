package iwiw.repository;

import iwiw.model.Message;
import iwiw.model.User;

import java.util.List;

public interface MessageRepository {

    void save(Message message);

    Message findById(Integer id);

    List<Message> findBySubject(String subject);

    List<Message> findAllByUser(User user);


    void delete(Message message);

    void deleteById(Integer id);
}
