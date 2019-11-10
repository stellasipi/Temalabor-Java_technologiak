package iwiw.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User creatorUser;

    private Date creationTime;
    private String title;
    private String text;

    public Note(Integer id, User creator, Date creationTime, String title, String text) {
        this.id = id;
        this.creatorUser = creator;
        this.creationTime = creationTime;
        this.title = title;
        this.text = text;
    }
}
