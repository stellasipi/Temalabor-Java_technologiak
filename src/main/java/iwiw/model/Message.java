package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Message {

    private Integer id;
    private String subject;
    private Date sentDate;
    private String body;
}
