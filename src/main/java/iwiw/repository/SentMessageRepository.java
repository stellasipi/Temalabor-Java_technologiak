package iwiw.repository;

import iwiw.model.SentMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentMessageRepository extends JpaRepository<SentMessage,Integer> {

    public List<Integer> listByFromId(Integer fromId);
    public List<Integer> listByToId(Integer toId);

}
