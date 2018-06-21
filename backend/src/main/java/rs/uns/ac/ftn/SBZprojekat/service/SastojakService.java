package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Sastojak;

import java.util.List;

public interface SastojakService {

    Sastojak save(Sastojak sastojak);

    Sastojak findOne(Long id);

    List<Sastojak> findAll();

    void remove(Sastojak sastojak);

    Sastojak findByNaziv(String naziv);
}
