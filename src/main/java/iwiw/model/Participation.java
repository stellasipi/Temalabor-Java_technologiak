package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Participation {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;
    private Integer eventId;

    private String comment;//evaluation in text
    private Integer value;//evaluation in number
}
