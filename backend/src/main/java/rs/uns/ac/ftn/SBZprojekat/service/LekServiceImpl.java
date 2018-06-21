package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Lek;
import rs.uns.ac.ftn.SBZprojekat.repository.LekRepository;

import java.util.List;

@Service
public class LekServiceImpl implements LekService{

    @Autowired
    private LekRepository lekRepository;

    @Override
    @Transactional(readOnly = true)
    public Lek findOne(Long id) {
        Lek lek = this.lekRepository.findOne(id);
        return lek;
    }

    @Override
    @Transactional(readOnly = false)
    public Lek save(Lek lek) {
        return this.lekRepository.save(lek);
    }


    @Override
    @Transactional(readOnly = false)
    public void remove(Lek lek) {
        lek.setDeleted(true);
        this.lekRepository.save(lek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lek> findAll(){
        return this.lekRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Lek findByNaziv(String naziv){
        return this.lekRepository.findByNaziv(naziv);
    }
}
