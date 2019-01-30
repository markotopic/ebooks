package rs.ac.uns.ftn.informatika.udd.vezbe01.dto;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Language;

import java.io.Serializable;

public class LanguageDTO implements Serializable {

    private Long id;
    private String name;

    public LanguageDTO() {
    }

    public LanguageDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public LanguageDTO(Language language) {
        this (language.getId(), language.getName());
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

}
