package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.SBZprojekat.model.Account;
import rs.uns.ac.ftn.SBZprojekat.repository.AccountRepository;
import rs.uns.ac.ftn.SBZprojekat.security.JWTUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findOne(Long id) {
        Account account = this.accountRepository.getOne(id);
        return account;
    }

    @Override
    @Transactional(readOnly = false)
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Account account) {
        account.setDeleted(true);
        this.accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUsername(String username) {
        Account account = this.accountRepository.findByUsername(username);
        if(account != null)
            return false;
        return true;
    }

}
