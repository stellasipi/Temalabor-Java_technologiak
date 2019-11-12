package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User sender;

    @Builder.Default
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

//    @OneToMany(mappedBy = "message")
//    private List<MessageUser> sentMessages;

    private Integer addresseeId; //címzett TODO: EZZEL KEZDENI VMIT, H NE INTEGER LEGYEN
    private String subject;
    private Date sentDate;
    private String body;

    public Message(String subject, Date sentDate, String body){
        this.subject=subject;
        this.sentDate=sentDate;
        this.body=body;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
        tag.getMessages().add(this);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
        tag.getMessages().remove(this);
    }
}
