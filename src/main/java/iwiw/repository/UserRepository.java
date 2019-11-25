package iwiw.repository;


import iwiw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    //public User findById(Integer id);

}


