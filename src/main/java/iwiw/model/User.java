package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Builder.Default
    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL)
    private  Set<Note> notes = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL)
    private  Set<Event> createdEvents = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  Set<UserEvent> participatedEvents = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private  Set<Message> sentMessages = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "addressee", cascade = CascadeType.ALL)
    private  Set<Message> receivedMessages = new HashSet<>();

    /*
        https://stackoverflow.com/questions/1656113/hibernate-recursive-many-to-many-association-with-the-same-entity
        top válasz
         */
    @Builder.Default
    @ManyToMany
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="personId"),
            inverseJoinColumns=@JoinColumn(name="friendId")
    )
    private Set<User> friends = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="personId")
    )
    private Set<User> friendOf = new HashSet<>();

    private String name;
    private String password;
    private String userName;

    public User(Integer id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userName = userName;
    }


    public void addNote(Note note){
       note.setCreatorUser(this);
       this.notes.add(note);
   }

    public void removeNote(Note note){
       note.setCreatorUser(null);
       this.notes.remove(note);
   }

   public void addCreatedEvent(Event event){
       event.setCreatorUser(this);
       this.createdEvents.add(event);
   }

   public void removeCreatedEvent(Event event){
       event.setCreatorUser(null);
       this.createdEvents.remove(event);
   }

    public void addOutgoingMessage(Message message){
        message.setSender(this);
        this.sentMessages.add(message);
    }

    public void addIncomingMessage(Message message){
        message.setAddressee(this);
        this.receivedMessages.add(message);
    }


    public void addFriend(User newFriend){
       this.friends.add(newFriend);
       this.friendOf.add(newFriend);
    }


    public void removeFriend(User postFriend) {

        this.friends.remove(postFriend);
        this.friendOf.remove(postFriend);
        postFriend.friends.remove(this);
        postFriend.friendOf.remove(this);
    }

    public void addParticipatedEvent(Event event, String comment){
       UserEvent userEvent = UserEvent.builder().user(this).event(event).comment(comment).build();
       userEvent.setId(new UserEventId(this.id,event.getId()));
       participatedEvents.add(userEvent);
       event.getParticipatingUsers().add(userEvent);

    }

    public boolean checkPassword(String passwordToCheck){
       if(password.equals(passwordToCheck)){
           return true;
       }
       else{
           return false;
       }
    }


}
