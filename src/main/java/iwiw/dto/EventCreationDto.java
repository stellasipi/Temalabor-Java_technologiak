package iwiw.dto;

import lombok.Data;

@Data
public class EventCreationDto {
    private String name;
    private String place_name;
    private String place_city;
    private String place_country;
    private String date;
    //private String invited;
}
