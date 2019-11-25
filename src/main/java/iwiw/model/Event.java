package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User creatorUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private Place place;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<UserEvent> participatingUsers = new HashSet<>();

    private String name;
    private Date date;

    public Event(Integer id, User creator, String name, Date date, Place place) {
        this.id = id;
        this.creatorUser = creator;
        this.name = name;
        this.date = date;
        this.place = place;
    }

}
