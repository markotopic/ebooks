package rs.ac.uns.ftn.informatika.udd.vezbe01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.User;
import rs.ac.uns.ftn.informatika.udd.vezbe01.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOne(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().equals(username) && user.getPassword().equals(password))
            return user;
        else
            return null;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().equals(username))
            return user;
        else
            return null;
    }


}
