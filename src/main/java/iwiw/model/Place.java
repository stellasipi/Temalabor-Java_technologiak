package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Place {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String city;
    private String country;

    @OneToMany(mappedBy = "place")
    private List<Event> events;

    public Place(Integer id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public void addEvent(Event event){

        event.setPlace(this);
        if(this.events == null){
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }
}
