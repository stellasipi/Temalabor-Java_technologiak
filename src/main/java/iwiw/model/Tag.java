package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Builder.Default
    @ManyToMany(mappedBy = "tags")
    private Set<Message> messages = new HashSet<>();

    private String name;

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
        messages = new HashSet<>();
    }
}
