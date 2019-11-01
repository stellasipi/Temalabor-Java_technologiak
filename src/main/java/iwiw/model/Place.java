package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Place {

    private Integer id;
    private String name;
    private String city;
    private String country;
    private List<Event> events;
}
