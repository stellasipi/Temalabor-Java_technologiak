package iwiw.repository;

import iwiw.model.User;
import iwiw.model.UserConnection;

import java.util.List;

public interface UserConnectionRepository {

    void save(UserConnection connection);

    UserConnection findById(Integer id);

    List<UserConnection> findByParticipantId(Integer id);

    UserConnection findByTwoParticipants(User user1, User user2);

    void delete(UserConnection connection);

    void deleteById(Integer id);
}
