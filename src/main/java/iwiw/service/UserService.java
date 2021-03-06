package iwiw.service;

import iwiw.model.*;
import iwiw.dto.UserCreationDto;
import iwiw.repository.EventRepository;
import iwiw.repository.NotesRepository;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    EventRepository eventRepository;

    @Transactional
    public void changeUserName(User user, String newUserName){
        userRepository.findById(user.getId()).get().setUserName(newUserName);
        userRepository.save(user);
    }


    //Visszatérési érték true, ha sikerült a jelszóváltoztatás, false ha sikertelen (rossz jelenlegi jelszót adott meg pl.)
    @Transactional
    public boolean changePassword(User user, String oldPassword, String newPassword){ //old password biztonsági okokból, hogy a saját jelszavát tudja csak változtatni
        User userToChange = userRepository.findById(user.getId()).get();
        if(userToChange.checkPassword(oldPassword)){
            user.setPassword(newPassword);
            userRepository.save(userToChange);
            return true;
        }
        else{
            return false;
        }
    }

    @Transactional
    public void deleteMessagesWithASpecificTag(User user, Tag tagSpam) {
        User userToChange=userRepository.findById(user.getId()).get();
        for(Iterator<Message> iterator=userToChange.getReceivedMessages().iterator();iterator.hasNext();){
            Message message=iterator.next();
            if(message.getTags().contains(tagSpam)){
                iterator.remove();
            }
        }
    }

    @Transactional
    public void createEvent(User user, Event event){
        user.addCreatedEvent(event);
        userRepository.save(user);
    }

    @Transactional
    public void createUser(UserCreationDto user) {
        User newUser = User.builder().name(user.getFullName()).userName(user.getUserName()).password(user.getPassword())
                    .build();
        userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public boolean validateAccountLogin(String userName, String password){
        User user = userRepository.findByNameIs(userName);

        if(user == null)
            return false;

        return password.equals(user.getPassword());
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName){
        return userRepository.findByNameIs(userName);
    }

    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<User> findAll() { return userRepository.findAll(); }

    @Transactional
    public void removeFriend(User a, User b){ a.removeFriend(b); }

    @Transactional
    public void addFriend(User a, User b){ a.addFriend(b); }

    @Transactional
    public void removeNote(User a, Integer noteId){
        Note note = notesRepository.findById(noteId).get();
        a.removeNote(note);
        notesRepository.delete(note);
    }

    @Transactional
    public void addNote(User a, Note b){
        a.addNote(b);
    }

    @Transactional
    public HashSet<Event> getParticipatedEvents(User user){
        HashSet<Event> participatedEvents=new HashSet<>();
        Set<UserEvent> userEvents=userRepository.findById(user.getId()).get().getParticipatedEvents();
        for(UserEvent userEvent:userEvents){
            participatedEvents.add(userEvent.getEvent());
        }
        return  participatedEvents;
    }


}
