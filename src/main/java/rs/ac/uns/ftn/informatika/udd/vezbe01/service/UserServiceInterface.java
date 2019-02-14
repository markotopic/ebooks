package rs.ac.uns.ftn.informatika.udd.vezbe01.service;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.User;

import java.util.List;

public interface UserServiceInterface {

    User findOne(Long userId);

    List<User> findAll();

    User save(User user);

    void remove(Long id);

    User findByUsernameAndPassword(String Username, String password);

    User findByUsername(String username);

}
