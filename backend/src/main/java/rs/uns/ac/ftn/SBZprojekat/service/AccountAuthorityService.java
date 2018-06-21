package rs.uns.ac.ftn.SBZprojekat.service;

import rs.uns.ac.ftn.SBZprojekat.model.AccountAuthority;

public interface AccountAuthorityService {

    AccountAuthority save(AccountAuthority accountAuthority);

    void remove(Long id);
}
