package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;

import java.util.List;

public interface DijagnozaService {

    Dijagnoza save(Dijagnoza bolest);

    Dijagnoza findOne(Long id);

    List<Dijagnoza> findAll();

    void remove(Long id);
}
