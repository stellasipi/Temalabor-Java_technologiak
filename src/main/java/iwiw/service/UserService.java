package iwiw.service;

import iwiw.model.User;
import iwiw.repository.UserRepository;

public class UserService {
    UserRepository userRepository;

    public void changeUserName(User user, String newUserName){
        user.setUserName(newUserName);
        userRepository.update(user);
    }

    public void changePassword(User user, String newPassword){
        user.setPassword(newPassword);
        userRepository.update(user);
    }
}
