package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Pacijent;
import rs.uns.ac.ftn.SBZprojekat.repository.PacijentRepository;

import java.util.List;

@Service
public class PacijentServiceImpl implements PacijentService{

    @Autowired
    private PacijentRepository pacijentRepository;


    @Override
    @Transactional(readOnly = true)
    public Pacijent findOne(Long id) {
        Pacijent pacijent = this.pacijentRepository.findOne(id);
        return pacijent;
    }

    @Override
    @Transactional(readOnly = false)
    public Pacijent save(Pacijent pacijent) {
        return this.pacijentRepository.save(pacijent);
    }


    @Override
    @Transactional(readOnly = false)
    public void remove(Long id) {
        this.pacijentRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pacijent> findAll(){
        return this.pacijentRepository.findAll();
    }
}
