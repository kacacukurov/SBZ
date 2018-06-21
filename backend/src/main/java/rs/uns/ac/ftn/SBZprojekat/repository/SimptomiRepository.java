package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;

public interface SimptomiRepository extends JpaRepository<Simptomi, Long> {

    Simptomi findByNaziv(String naziv);
}
