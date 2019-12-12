package iwiw.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreationDto {

    @NotNull
    @Size(min = 4, max = 100)
    private String fullName = "";

    @NotNull
    @Size(min =4, max = 100)
    private String userName = "";

    @Size(min = 8)
    private String password = "";

    @AssertTrue
    private boolean accepted = false;
}
