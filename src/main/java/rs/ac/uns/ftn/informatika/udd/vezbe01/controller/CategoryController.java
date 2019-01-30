package rs.ac.uns.ftn.informatika.udd.vezbe01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.udd.vezbe01.dto.CategoryDTO;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Category;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.CategoryServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/categories")
public class CategoryController {

    @Autowired
    CategoryServiceInterface categoryServiceInterface;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getCategories(){

        List<Category> uModels = categoryServiceInterface.findAll();
        List<CategoryDTO> uploadModels = new ArrayList<CategoryDTO>();
        for (Category u : uModels){
            uploadModels.add(new CategoryDTO(u));
        }

        return new ResponseEntity<List<CategoryDTO>>(uploadModels, HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public ResponseEntity<String> saveCategories(@ModelAttribute CategoryDTO b){
        Category a = new Category();

        a.setName(b.getName());

        a = categoryServiceInterface.save(a);
        return new ResponseEntity<String>(new CategoryDTO(a).toString(), HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<Void> updateCategories(@ModelAttribute CategoryDTO categoryDTO, @PathVariable("id") Long id){
        Category category = categoryServiceInterface.findOne(id);

        if(category == null) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        category.setName(categoryDTO.getName());
        category = categoryServiceInterface.save(category);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        Category category = categoryServiceInterface.findOne(id);
        if(category != null) {
            categoryServiceInterface.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
