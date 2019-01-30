package rs.ac.uns.ftn.informatika.udd.vezbe01.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="umodel")
public class UModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name="Title", columnDefinition="VARCHAR(40)")
    private String title;

    @Column(name="Author", columnDefinition="VARCHAR(20)")
    private String author;

    @Column(name="Keywords", columnDefinition="VARCHAR(40)")
    private String keywords;

    @Column(name="Files", columnDefinition="VARCHAR(150)")
    private String files;

    @ManyToOne
    @JoinColumn(name="cat_id", referencedColumnName="cat_id", nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="lan_id", referencedColumnName="lan_id", nullable=false)
    private Language language;

    public UModel() {
    }

    public UModel(String title, String author, String keywords, String files, Category category, Language language) {
        this.title = title;
        this.author = author;
        this.keywords = keywords;
        this.files = files;
        this.category = category;
        this.language = language;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
