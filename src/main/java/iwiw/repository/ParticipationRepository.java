package iwiw.repository;

import iwiw.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation,Integer> {

//    public List<Participation> listByUserId(Integer userId);
//    public List<Participation> listByEventId(Integer eventId);
//    public void update(Participation participation);

}
