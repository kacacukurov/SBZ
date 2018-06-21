package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Sastojak;

public interface SastojakRepository extends JpaRepository<Sastojak, Long> {

    Sastojak findByNaziv(String naziv);
}
