package rs.ac.uns.ftn.informatika.udd.vezbe01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.informatika.udd.vezbe01.dto.LanguageDTO;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Language;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.LanguageServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/langs")

public class LanguageController {

    @Autowired
    LanguageServiceInterface languageServiceInterface;

    @GetMapping("/all")
    public ResponseEntity<List<LanguageDTO>> getUploads(){

        List<Language> uModels = languageServiceInterface.findAll();
        List<LanguageDTO> uploadModels = new ArrayList<LanguageDTO>();
        for (Language u : uModels){
            uploadModels.add(new LanguageDTO(u));
        }

        return new ResponseEntity<List<LanguageDTO>>(uploadModels, HttpStatus.OK);
    }

}
