package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
