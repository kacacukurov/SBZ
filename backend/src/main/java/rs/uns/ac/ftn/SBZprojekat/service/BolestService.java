package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Bolest;

import java.util.List;

public interface BolestService {

    Bolest save(Bolest bolest);

    Bolest findOne(Long id);

    List<Bolest> findAll();

    void remove(Bolest bolest);

    Bolest findByNaziv(String naziv);
}
