package iwiw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//részvétel ??
public class Participation {

    private Integer id;
    private Integer userId;
    private Integer eventId;

    private String comment;
    // private ?? Value;
}
