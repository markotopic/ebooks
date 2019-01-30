package rs.ac.uns.ftn.informatika.udd.vezbe01.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cat_id", unique=true, nullable=false)
    private Long Id;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="category")
    private Set<UModel> uModels = new HashSet<UModel>();

    public Category() {
    }

    public Category(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UModel> getuModels() {
        return uModels;
    }

    public void setuModels(Set<UModel> uModels) {
        this.uModels = uModels;
    }
}
