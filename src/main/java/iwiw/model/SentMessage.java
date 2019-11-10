package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Deprecated
public class SentMessage { //szükségtelen, közben rájöttük hogy OneToMany kapcsolat lesz

    @Id
    @GeneratedValue
    private Integer id;

    private Integer toId;
    private Integer fromId;
    private Integer messageId;

    public SentMessage(Integer toId,Integer fromId,Integer messageId){
        this.toId=toId;
        this.fromId=fromId;
        this.messageId=messageId;
    }

}
