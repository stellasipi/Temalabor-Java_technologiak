package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Event {


    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User creatorUser;

    @ManyToOne
    private Place place;

    @OneToMany(mappedBy = "event")
    private List<UserEvent> participatingUsers;

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
