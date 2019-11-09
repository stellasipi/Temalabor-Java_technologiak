package iwiw.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Message> messages;

    public Tag(){}

    public Tag(String name) {
        this.name = name;
    }

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
