package rs.ac.uns.ftn.informatika.udd.vezbe01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.UModel;
import rs.ac.uns.ftn.informatika.udd.vezbe01.lucene.model.UploadModel;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.UmodelService;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.UmodelServiceInterface;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/books")
public class UmodelController {

    @Autowired
    private UmodelServiceInterface umodelServiceInterface;

    @Autowired
    private UmodelService umodelService;

    @GetMapping("/all")
    public ResponseEntity<List<UploadModel>> getBooks(){

        List<UModel> uModels = umodelServiceInterface.findAll();
        List<UploadModel> uploadModels = new ArrayList<UploadModel>();
        for (UModel u : uModels){
            uploadModels.add(new UploadModel(u));
        }

        return new ResponseEntity<List<UploadModel>>(uploadModels, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
        UModel book = umodelServiceInterface.findOne(id);
        if(book != null) {
            umodelServiceInterface.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }



}
