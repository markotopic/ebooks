package rs.ac.uns.ftn.informatika.udd.vezbe01.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {

    public enum Role{
        ADMIN,
        USER,
        VISITOR
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id", unique=true, nullable=false)
    private Long Id;

    @Column(name="firstName", unique=false, nullable=false)
    private String firstName;

    @Column(name="lastName", unique=false, nullable=false)
    private String lastName;

    @Column(name="username", unique=false, nullable=false)
    private String username;

    @Column(name="password", unique=false, nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", unique=false, nullable=false)
    private Role role;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String password, Role role) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
