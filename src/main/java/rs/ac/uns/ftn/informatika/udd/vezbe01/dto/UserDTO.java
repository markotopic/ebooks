package rs.ac.uns.ftn.informatika.udd.vezbe01.dto;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private User.Role role;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String username, String password, User.Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}
