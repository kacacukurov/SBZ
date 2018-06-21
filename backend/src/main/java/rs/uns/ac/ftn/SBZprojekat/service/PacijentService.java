package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Pacijent;

import java.util.List;

public interface PacijentService {

    Pacijent save(Pacijent pacijent);

    Pacijent findOne(Long id);

    List<Pacijent> findAll();

    void remove(Pacijent pacijent);

    Pacijent findByJmbg(String jmbg);
}
