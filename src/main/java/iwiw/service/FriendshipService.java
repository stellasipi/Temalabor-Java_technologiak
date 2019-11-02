package iwiw.service;

import iwiw.model.User;
import iwiw.model.UserConnection;
import iwiw.repository.UserConnectionRepository;

public class FriendshipService {

    private UserConnectionRepository connectionRepository;

    public void newFriendshipBetween(User user1, User user2){

        connectionRepository.save( createNewConnectionBetween(user1,user2) );

    }

    private UserConnection createNewConnectionBetween(User user1, User user2){

        UserConnection newConnection = new UserConnection();
        newConnection.setFirstUserId(user1.getID());
        newConnection.setSecondUserId(user2.getID());
        //Plusz egyedi id beállítás majd valahogy

        return newConnection;

    }

    //stb, stb...
}
