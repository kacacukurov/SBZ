package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.Account;
import rs.uns.ac.ftn.SBZprojekat.model.AccountAuthority;
import rs.uns.ac.ftn.SBZprojekat.model.Authority;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;
import rs.uns.ac.ftn.SBZprojekat.security.JWTUtils;
import rs.uns.ac.ftn.SBZprojekat.service.AccountAuthorityService;
import rs.uns.ac.ftn.SBZprojekat.service.AccountService;
import rs.uns.ac.ftn.SBZprojekat.service.AuthorityService;
import rs.uns.ac.ftn.SBZprojekat.web.dto.AccountDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.LoginDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.RegistracijaDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.TokenDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/korisnik")
public class KorisnikController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AccountAuthorityService accountAuthorityService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(
            @RequestBody LoginDTO loginDTO) {
        try {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            Account account = this.accountService.findByUsername(loginDTO.getUsername());
            System.out.println(account.getAccountAuthorities().get(0).getAuthority().getIme());
            UserDetails details = userDetailsService.loadUserByUsername(account.getUsername());

            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details));
            System.out.println(userToken.getValue());
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity registrujAdmina(@RequestBody RegistracijaDTO registracijaDTO) {

        this.accountService.checkUsername(registracijaDTO.getLoginAccount().getUsername());

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pass = bc.encode(registracijaDTO.getLoginAccount().getPassword());
        Account account = new Account(registracijaDTO.getLoginAccount().getUsername(), pass,
                registracijaDTO.getIme(), registracijaDTO.getPrezime());


        Authority authority = this.authorityService.findByIme("ADMIN");

        AccountAuthority accountAuthority = new AccountAuthority(account, authority);
        account.getAccountAuthorities().add(accountAuthority);
        account = this.accountService.save(account);

        accountAuthority.setAccount(account);
        accountAuthority.setAuthority(authority);
        this.accountAuthorityService.save(accountAuthority);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/doktor",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity registrujLekara(@RequestBody RegistracijaDTO registracijaDTO) {

        this.accountService.checkUsername(registracijaDTO.getLoginAccount().getUsername());

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pass = bc.encode(registracijaDTO.getLoginAccount().getPassword());
        Account account = new Account(registracijaDTO.getLoginAccount().getUsername(), pass,
                registracijaDTO.getIme(), registracijaDTO.getPrezime());


        Authority authority = this.authorityService.findByIme("DOKTOR");

        AccountAuthority accountAuthority = new AccountAuthority(account, authority);
        account.getAccountAuthorities().add(accountAuthority);
        account = this.accountService.save(account);


        accountAuthority.setAccount(account);
        accountAuthority.setAuthority(authority);
        this.accountAuthorityService.save(accountAuthority);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/izmeniNalog",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity izmeniNalog(@RequestBody RegistracijaDTO registracijaDTO) {

        Account account = this.accountService.findByUsername(registracijaDTO.getLoginAccount().getUsername());

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pass = bc.encode(registracijaDTO.getLoginAccount().getPassword());
        account.setIme(registracijaDTO.getIme());
        account.setPrezime(registracijaDTO.getPrezime());
        account.setPassword(pass);

        account = this.accountService.save(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSveDoktore(@RequestParam String uloga) {

        List<Account> accounts = this.accountService.findAll();

        List<AccountDTO> accountDTOS = new ArrayList<>();

        for(Account account: accounts){
            for(AccountAuthority accountAuthority: account.getAccountAuthorities()){
                if (accountAuthority.getAuthority().getIme().equals(uloga)){
                    accountDTOS.add(new AccountDTO(account.getUsername(), account.getIme(), account.getPrezime()));
                }
            }
        }
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity obrisiDoktore(@RequestParam String username){

        Account account = this.accountService.findByUsername(username);
        if(account == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.accountService.remove(account);

        return new ResponseEntity(HttpStatus.OK);
    }
}
