package iwiw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participation {

    private Integer id;
    private Integer userId;
    private Integer eventId;

    private String comment;//evaluation in text
    private Integer value;//evaluation in number
}
