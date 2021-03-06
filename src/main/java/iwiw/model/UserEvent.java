package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * User És Event kapcsolótáblája, mert több-több és vannak plusz attribútumai
 */
public class UserEvent {

    @EmbeddedId
    private UserEventId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("eventId")
    private Event event;

    private String comment;//evaluation in text
    private Integer value;//evaluation in number


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEvent userEvent = (UserEvent) o;
        return Objects.equals(id, userEvent.id) &&
                Objects.equals(user, userEvent.user) &&
                Objects.equals(event, userEvent.event) &&
                Objects.equals(comment, userEvent.comment) &&
                Objects.equals(value, userEvent.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, event, comment, value);
    }
}
