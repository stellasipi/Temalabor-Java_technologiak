package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SentMessage {

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
