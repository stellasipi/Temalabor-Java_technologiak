package iwiw.repository;

import iwiw.model.Event;
import iwiw.model.Place;

import java.util.Date;
import java.util.List;

public interface EventRepository {

    void save(Event event);

    Event findById(Integer id);

    List<Event> findByCreatorId(Integer id);

    List<Event> findByName(String name);

    List<Event> findByDate(Date date);

    List<Event> findByPlace(Place place);

    Event update(Event event);

    void delete(Event event);

    void deleteById(Integer id);


}
