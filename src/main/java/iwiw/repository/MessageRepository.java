package iwiw.repository;

import iwiw.model.Message;
import iwiw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

//    List<Message> findBySubject(String subject);
//    List<Message> findAllByUser(User user);

}
