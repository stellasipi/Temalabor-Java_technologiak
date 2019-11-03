package iwiw.repository;

import iwiw.model.Participation;

import java.util.List;

public interface ParticipationRepository {
    
    public Participation findById(Integer id);
    public List<Participation> listByUserId(Integer userId);
    public List<Participation> listByEventId(Integer eventId);

}
