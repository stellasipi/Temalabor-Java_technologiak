package iwiw.repository;

import iwiw.model.Message;
import iwiw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    ArrayList<Message> findMessagesByAddressee(User user); //Címzett alapján levelek listázása

}
