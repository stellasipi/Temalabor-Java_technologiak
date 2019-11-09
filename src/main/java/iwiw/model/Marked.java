package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Marked {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Integer tagId;

    @ManyToOne
    private Integer messageId;

    public Marked(Integer tagId, Integer messageId){
        this.tagId=tagId;
        this.messageId=messageId;
    }
}
