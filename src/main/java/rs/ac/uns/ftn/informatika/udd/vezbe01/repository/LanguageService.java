package rs.ac.uns.ftn.informatika.udd.vezbe01.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Language;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.LanguageServiceInterface;

import java.util.List;

@Service
public class LanguageService implements LanguageServiceInterface {

    @Autowired
    LanguageRepository languageRepository;

    @Override
    public Language findOne(Long id) {
        return languageRepository.findById(id).get();
    }

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
