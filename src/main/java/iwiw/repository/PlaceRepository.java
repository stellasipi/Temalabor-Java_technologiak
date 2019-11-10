package iwiw.repository;

import iwiw.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Integer> {


//    Place findByName(String name);
//    List<Place> findByCity(String city);
//    List<Place> findByCountry(String country);
//    Place update(Place place);

}
