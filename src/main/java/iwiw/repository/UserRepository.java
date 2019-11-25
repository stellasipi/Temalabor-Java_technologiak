package iwiw.repository;


import iwiw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    //public User findById(Integer id);

    List<User> findByName(String name);

}


