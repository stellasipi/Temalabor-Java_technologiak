package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Note {

    private Integer id;
    private Integer userId;
    private Date creationTime;
    private String title;
    private String text;

}