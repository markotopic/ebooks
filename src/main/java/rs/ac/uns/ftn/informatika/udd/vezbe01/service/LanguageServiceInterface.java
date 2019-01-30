package rs.ac.uns.ftn.informatika.udd.vezbe01.service;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Language;

import java.util.List;

public interface LanguageServiceInterface {

    Language findOne(Long id);

    List<Language> findAll();

}
