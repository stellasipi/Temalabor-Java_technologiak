package iwiw.repository;


import iwiw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    //public User findById(Integer id);

    List<User> findByName(String name);

    @Query("select u from User u where u.userName = ?1")
    User findByNameis(String name);

}


