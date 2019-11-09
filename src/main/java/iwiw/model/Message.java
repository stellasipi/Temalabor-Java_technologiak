package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User sender;

    @ManyToMany
    private List<Tag> tags;

//    @OneToMany(mappedBy = "message")
//    private List<MessageUser> sentMessages;

    private User addressee; //címzett
    private String subject;
    private Date sentDate;
    private String body;

    public Message(String subject, Date sentDate, String body){
        this.subject=subject;
        this.sentDate=sentDate;
        this.body=body;
    }
}
