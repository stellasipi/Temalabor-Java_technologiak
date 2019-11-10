package iwiw.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;

    @Builder.Default
    @ManyToMany(mappedBy = "tags")
    private Set<Message> messages = new HashSet<>();

    private String name;

}
