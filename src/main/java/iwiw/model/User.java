package iwiw.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class User {

    private Integer ID;
    private String name;
    private String password;
    private String userName;
    private List<Note> notes;
    private List<Event> createdEvents;


}
