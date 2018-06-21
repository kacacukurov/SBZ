package rs.uns.ac.ftn.SBZprojekat.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Bolest;
import rs.uns.ac.ftn.SBZprojekat.repository.BolestRepository;

import java.util.List;


@Service
public class BolestServiceImpl implements BolestService{

    @Autowired
    private BolestRepository bolestRepository;

    @Override
    @Transactional(readOnly = true)
    public Bolest findOne(Long id) {
        Bolest bolest = this.bolestRepository.findOne(id);
        return bolest;
    }

    @Override
    @Transactional(readOnly = false)
    public Bolest save(Bolest bolest) {
        return this.bolestRepository.save(bolest);
    }


    @Override
    @Transactional(readOnly = false)
    public void remove(Bolest bolest) {
        bolest.setDeleted(true);
        this.bolestRepository.save(bolest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bolest> findAll(){
        return this.bolestRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Bolest findByNaziv(String naziv){
        return this.bolestRepository.findByNazivBolesti(naziv);
    }
}
