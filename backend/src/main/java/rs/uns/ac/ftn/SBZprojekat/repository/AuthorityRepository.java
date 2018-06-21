package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Account;
import rs.uns.ac.ftn.SBZprojekat.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority save(Authority authority);

    Authority findByIme(String ime);
}
