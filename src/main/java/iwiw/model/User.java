package iwiw.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    private String userName;



    @OneToMany(mappedBy = "creatorUser")
    private List<Note> notes;

    @OneToMany(mappedBy = "creatorUser")
    private List<Event> createdEvents;

    @OneToMany(mappedBy = "user")
    private List<Event> participatedEvents;

    @OneToMany(mappedBy = "user")
    private List<Message> sentMessages;
}
