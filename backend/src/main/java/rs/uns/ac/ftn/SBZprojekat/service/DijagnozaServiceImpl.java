package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;
import rs.uns.ac.ftn.SBZprojekat.repository.DijagnozaRepository;

import java.util.List;

@Service
public class DijagnozaServiceImpl implements DijagnozaService{

    @Autowired
    private DijagnozaRepository dijagnozaRepository;

    @Override
    @Transactional(readOnly = true)
    public Dijagnoza findOne(Long id) {
        Dijagnoza dijagnoza = this.dijagnozaRepository.findOne(id);
        return dijagnoza;
    }

    @Override
    @Transactional(readOnly = false)
    public Dijagnoza save(Dijagnoza dijagnoza) {
        return this.dijagnozaRepository.save(dijagnoza);
    }


    @Override
    @Transactional(readOnly = false)
    public void remove(Long id) {
        this.dijagnozaRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dijagnoza> findAll(){
        return this.dijagnozaRepository.findAll();
    }
}
