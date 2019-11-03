package iwiw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Marked {

    private Integer id;
    private Integer tagId;
    private Integer messageId;

    public Marked(Integer tagId, Integer messageId){
        this.tagId=tagId;
        this.messageId=messageId;
    }
}
