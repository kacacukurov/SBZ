package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Lek;

import java.util.List;

public interface LekService {

    Lek save(Lek lek);

    Lek findOne(Long id);

    List<Lek> findAll();

    void remove(Lek lek);

    Lek findByNaziv(String naziv);
}
