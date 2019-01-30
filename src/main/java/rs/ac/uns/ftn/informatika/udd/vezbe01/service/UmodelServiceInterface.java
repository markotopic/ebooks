package rs.ac.uns.ftn.informatika.udd.vezbe01.service;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.UModel;

import java.util.List;

public interface UmodelServiceInterface {

    UModel findOne(Long id);

    List<UModel> findAll();

    UModel save(UModel uModel);

    void remove(Long id);

}
