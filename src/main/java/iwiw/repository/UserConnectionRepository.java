package iwiw.repository;

import iwiw.model.User;
import iwiw.model.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConnectionRepository extends JpaRepository<UserConnection,Integer> {

//    List<UserConnection> findByParticipantId(Integer id);
//    UserConnection findByTwoParticipants(User user1, User user2);

}
