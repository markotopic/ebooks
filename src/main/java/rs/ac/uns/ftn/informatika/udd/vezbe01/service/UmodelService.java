package rs.ac.uns.ftn.informatika.udd.vezbe01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.UModel;
import rs.ac.uns.ftn.informatika.udd.vezbe01.repository.UmodelRepository;

import java.util.List;

@Service
public class UmodelService implements UmodelServiceInterface {

    @Autowired
    UmodelRepository umodelRepository;

    @Override
    public UModel findOne(Long id) {
        return umodelRepository.findById(id).get();
    }

    @Override
    public List<UModel> findAll() {
        return umodelRepository.findAll();
    }

    @Override
    public UModel save(UModel uModel) {
        return umodelRepository.save(uModel);
    }

    @Override
    public void remove(Long id) {
        umodelRepository.deleteById(id);
    }

}
