package rs.ac.uns.ftn.informatika.udd.vezbe01.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="languages")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="lan_id", unique=true, nullable=false)
    private Long id;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="language")
    private Set<UModel> uModels = new HashSet<UModel>();

    public Language() {
    }

    public Language(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
