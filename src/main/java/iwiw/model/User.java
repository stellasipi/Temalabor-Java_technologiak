package iwiw.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Builder.Default
    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL)
    private  Set<Note> notes = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "creatorUser")
    private  Set<Event> createdEvents = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private  Set<UserEvent> participatedEvents = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "sender")
    private  Set<Message> sentMessages = new HashSet<>();

    /*
    https://stackoverflow.com/questions/1656113/hibernate-recursive-many-to-many-association-with-the-same-entity
    top válasz
     */
    @ManyToMany
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="personId"),
            inverseJoinColumns=@JoinColumn(name="friendId")
    )
    private Set<User> friends;

    @ManyToMany
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="personId")
    )
    private Set<User> friendOf;

    private String name;
    private String password;
    private String userName;

   public void addNote(Note note){
       initSets(note);
       note.setCreatorUser(this);
       this.notes.add(note);
   }


    public void removeNote(Note note){
       note.setCreatorUser(null);
       this.notes.remove(note);
   }

   public void addEvent(Event event){
       event.setCreatorUser(this);
       this.createdEvents.add(event);
   }

   public void removeEvent(Event event){
       event.setCreatorUser(null);
       this.createdEvents.remove(event);
   }

    public void addMessage(Message message){
        message.setSender(this);
        this.sentMessages.add(message);
    }

    public void removeMessage(Message message){
        message.setSender(null);
        this.sentMessages.remove(message);
    }

    public void addFriend(User newFriend){
       initSets(newFriend);
       this.friends.add(newFriend);
       this.friendOf.add(newFriend);
       newFriend.friends.add(this);
       newFriend.friendOf.add(this);
    }

    public void removeFriend(User postFriend){
       initSets(postFriend);
       this.friends.remove(postFriend);
       this.friendOf.remove(postFriend);
       postFriend.friends.remove(this);
       postFriend.friendOf.remove(this);
    }

    private void initSets(User newFriend) {
       if(friends == null)
           friends = new HashSet<>();

       if(friendOf == null)
           friendOf = new HashSet<>();

       if(newFriend.friends == null)
           newFriend.friends = new HashSet<>();

       if(newFriend.friendOf == null)
           newFriend.friendOf = new HashSet<>();

   }
    private void initSets(Note note) {
       if(notes == null)
           notes = new HashSet<>();
    }
}
