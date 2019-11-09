package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Integer id;
    private String subject;
    private Date sentDate;
    private String body;

    public Message(String subject, Date sentDate, String body){
        this.subject=subject;
        this.sentDate=sentDate;
        this.body=body;
    }
}
