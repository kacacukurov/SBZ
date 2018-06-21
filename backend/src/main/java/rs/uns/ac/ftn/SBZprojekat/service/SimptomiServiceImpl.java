package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;
import rs.uns.ac.ftn.SBZprojekat.repository.SimptomiRepository;

import java.util.List;

@Service
public class SimptomiServiceImpl implements SimptomiService{

    @Autowired
    private SimptomiRepository simptomiRepository;


    @Override
    @Transactional(readOnly = true)
    public Simptomi findOne(Long id) {
        Simptomi simptomi = this.simptomiRepository.findOne(id);
        return simptomi;
    }

    @Override
    @Transactional(readOnly = false)
    public Simptomi save(Simptomi simptomi) {
        return this.simptomiRepository.save(simptomi);
    }


    @Override
    @Transactional(readOnly = false)
    public void remove(Simptomi simptomi) {
        simptomi.setDeleted(true);
        this.simptomiRepository.save(simptomi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Simptomi> findAll(){
        return this.simptomiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Simptomi findByNaziv(String naziv){
        return this.simptomiRepository.findByNaziv(naziv);
    }

}
