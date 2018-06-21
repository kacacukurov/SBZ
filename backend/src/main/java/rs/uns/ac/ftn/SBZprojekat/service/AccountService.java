package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findOne(Long id);

    Account save(Account account);

    Account findByUsername(String username);

    void remove(Account account);

    boolean checkUsername(String username);

}
