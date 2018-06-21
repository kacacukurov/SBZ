package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Lek;

public interface LekRepository extends JpaRepository<Lek, Long> {

    Lek findByNaziv(String naziv_leka);
}
