package iwiw.service;

import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FriendshipService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createNewFriendshipBetween(User user1, User user2){

        User firstFriend = userRepository.findById(user1.getId()).get();
        firstFriend.addFriend(user2);

        User secondFriend = userRepository.findById(user2.getId()).get();
        secondFriend.addFriend(user1);

        userRepository.save(user1);
        userRepository.save(user2);

    }

    @Transactional
    public void deleteFriendshipBetween(User user1, User user2){

        userRepository.findById(user1.getId()).get().removeFriend(user2);
        userRepository.save(user1);
        userRepository.save(user2);
    }


}
