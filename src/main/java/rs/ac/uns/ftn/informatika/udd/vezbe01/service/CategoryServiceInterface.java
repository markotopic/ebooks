package rs.ac.uns.ftn.informatika.udd.vezbe01.service;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {

    Category findOne(Long id);

    List<Category> findAll();

    Category save(Category category);

    void remove(Long id);

}
