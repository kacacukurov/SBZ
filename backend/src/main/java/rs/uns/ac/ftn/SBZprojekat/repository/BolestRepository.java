package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Bolest;

import java.util.List;

public interface BolestRepository extends JpaRepository<Bolest, Long> {

    Bolest save(Bolest log);

    Bolest findOne(Long id);

    List<Bolest> findAll();

    Bolest findByNazivBolesti(String naziv);

}
