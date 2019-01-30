package rs.ac.uns.ftn.informatika.udd.vezbe01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.udd.vezbe01.dto.UserDTO;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.User;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @GetMapping(value="/all")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userService.findAll();
        List<UserDTO> userDTO = new ArrayList<UserDTO>();
        for (User u : users) {
            userDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public ResponseEntity<String> saveUser(@ModelAttribute UserDTO b){
        User a = new User();

        a.setFirstName(b.getFirstName());
        a.setLastName(b.getLastName());
        a.setUsername(b.getUsername());
        a.setPassword(b.getPassword());
        a.setRole(b.getRole());

        a = userService.save(a);
        return new ResponseEntity<String>(new UserDTO(a).toString(), HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<String> updateUser(@ModelAttribute UserDTO userDTO, @PathVariable("id") Long id){
        User user = userService.findOne(id);

        if(user == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        user = userService.save(user);
        return new ResponseEntity<String>(new UserDTO(user).toString(), HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        User user = userService.findOne(id);
        if(user != null) {
            userService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
