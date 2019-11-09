package iwiw.service;

import iwiw.model.Event;
import iwiw.model.Participation;
import iwiw.model.User;
import iwiw.repository.ParticipationRepository;

import java.util.List;

public class ParticipationService {

    ParticipationRepository participationRepository;

    public void createEvaluation(User user, Event event,Integer value, String text){
        List<Participation> userEvents=participationRepository.listByUserId(user.getId());
        for(Participation participation:userEvents){
            if(participation.getEventId().equals(event.getId())){
                Participation temp=participationRepository.findById(participation.getId()).get();
                temp.setComment(text);
                temp.setValue(value);
                participationRepository.update(temp);
            }
        }
    }

}
