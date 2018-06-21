package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Sastojak;
import rs.uns.ac.ftn.SBZprojekat.repository.SastojakRepository;

import java.util.List;

@Service
public class SastojakServiceImpl implements SastojakService{

    @Autowired
    private SastojakRepository sastojakRepository;


    @Override
    @Transactional(readOnly = true)
    public Sastojak findOne(Long id) {
        Sastojak sastojak = this.sastojakRepository.findOne(id);
        return sastojak;
    }

    @Override
    @Transactional(readOnly = false)
    public Sastojak save(Sastojak sastojak) {
        return this.sastojakRepository.save(sastojak);
    }


    @Override
    @Transactional(readOnly = false)
    public void remove(Sastojak sastojak) {
        sastojak.setDeleted(true);
        this.sastojakRepository.save(sastojak);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sastojak> findAll(){
        return this.sastojakRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sastojak findByNaziv(String naziv){
        return this.sastojakRepository.findByNaziv(naziv);
    }
}
