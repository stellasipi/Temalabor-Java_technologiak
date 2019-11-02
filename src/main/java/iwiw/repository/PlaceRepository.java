package iwiw.repository;

import iwiw.model.Place;

import java.util.List;

public interface PlaceRepository {

    void save(Place place);

    Place findById(Integer id);

    Place findByName(String name);

    List<Place> findByCity(String city);

    List<Place> findByCountry(String country);

    void delete(Place place);

    void deleteById(Integer id);

    Place update(Place place);

}
