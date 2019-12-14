package iwiw.dto;

import lombok.Data;

@Data
public class MessageCreationDto {

    private String subject;
    private String text;
    private String addressee;
}
