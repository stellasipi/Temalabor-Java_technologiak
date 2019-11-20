package iwiw.service;

import iwiw.model.User;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
}
