package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
//@Builder
public class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User creatorUser;

    @Lob
    private String text;

    private Date creationTime;
    private String title;

    public Note(User creatorUser, String text, Date creationTime, String title) {
        this.creatorUser = creatorUser;
        this.text = text;
        this.creationTime = creationTime;
        this.title = title;
    }

    public Note(Integer id, User creator, Date creationTime, String title, String text) {
        this.id = id;
        this.creatorUser = creator;
        this.creationTime = creationTime;
        this.title = title;
        this.text = text;
    }
}
