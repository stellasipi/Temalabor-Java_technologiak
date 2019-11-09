package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UserConnection {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer firstUserId;
    private Integer secondUserId;

}
