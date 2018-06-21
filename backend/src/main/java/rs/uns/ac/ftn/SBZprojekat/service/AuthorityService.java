package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Authority;

import java.util.List;

public interface AuthorityService {

    Authority save(Authority authority);

    void remove(Long id);

    Authority findByIme(String ime);
}
