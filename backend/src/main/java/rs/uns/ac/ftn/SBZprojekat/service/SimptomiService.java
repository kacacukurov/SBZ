package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;

import java.util.List;

public interface SimptomiService {

    Simptomi save(Simptomi simptomi);

    Simptomi findOne(Long id);

    List<Simptomi> findAll();

    void remove(Simptomi simptomi);

    Simptomi findByNaziv(String naziv);
}
