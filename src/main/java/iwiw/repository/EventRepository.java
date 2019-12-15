package iwiw.repository;

import iwiw.model.Event;
import iwiw.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {

//    List<Event> findByCreatorId(Integer id);
//    List<Event> findByName(String name);
//    List<Event> findByDate(Date date);
//    List<Event> findByPlace(Place place);
//    Event update(Event event);

    @Modifying
    @Query("delete from Event e where e.id = :eventId")
    void delete(@Param("eventId")Integer eventId);

}
