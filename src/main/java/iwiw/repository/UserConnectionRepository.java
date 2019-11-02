package iwiw.repository;

import iwiw.model.UserConnection;

import java.util.List;

public interface UserConnectionRepository {

    void save(UserConnection connection);

    UserConnection findById(Integer id);

    List<UserConnection> findByParticipantId(Integer id);

    void delete(UserConnection connection);

    void deleteById(Integer id);
}
