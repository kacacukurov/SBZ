package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
}
