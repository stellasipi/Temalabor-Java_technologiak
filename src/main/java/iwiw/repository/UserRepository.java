package iwiw.repository;


import iwiw.model.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User findById(Integer id);

    List<User> findByName(String name);

    List<User> findAll();

    void delete(User user);

    void deleteById(Integer id);

    User update(User user);

}


