package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Event {

    private Integer ID;
    private String name;
    private Date date;
    private Place place;

}
