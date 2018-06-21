package rs.uns.ac.ftn.SBZprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.SBZprojekat.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
