package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;

import java.util.List;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long> {

    Dijagnoza save(Dijagnoza log);

    Dijagnoza findOne(Long id);

    List<Dijagnoza> findAll();
}
