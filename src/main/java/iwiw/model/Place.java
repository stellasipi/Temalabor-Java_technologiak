package iwiw.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
public class Place {

    @Id
    @GeneratedValue
    private Integer id;

    @Builder.Default
    @OneToMany(mappedBy = "place")
    private Set<Event> events = new HashSet<>();

    private String name;
    private String city;
    private String country;

    public Place(Integer id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public void addEvent(Event event){
        event.setPlace(this);
        this.events.add(event);
    }

    public void removeEvent(Event event){
        event.setPlace(null);
        this.events.remove(event);
    }
}
