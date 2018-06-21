package rs.uns.ac.ftn.SBZprojekat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Account;
import rs.uns.ac.ftn.SBZprojekat.model.AccountAuthority;
import rs.uns.ac.ftn.SBZprojekat.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(String.format("There is no account with username '%s'.", username));
        } else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (AccountAuthority accountAuthority: account.getAccountAuthorities()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(accountAuthority.getAuthority().getIme()));
            }

            return new org.springframework.security.core.userdetails.User(
                    account.getUsername(),
                    account.getPassword(),
                    grantedAuthorities);
        }
    }
}
