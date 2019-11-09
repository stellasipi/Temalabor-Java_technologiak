package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Event {


    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User creatorUser;

    private String name;
    private Date date;
    private Place place;

    public Event(Integer id, User creator, String name, Date date, Place place) {
        this.id = id;
        this.creatorUser = creator;
        this.name = name;
        this.date = date;
        this.place = place;
    }



}
