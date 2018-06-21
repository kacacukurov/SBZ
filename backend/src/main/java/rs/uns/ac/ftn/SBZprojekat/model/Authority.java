package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<AccountAuthority> accountAuthorities;

    public Authority() { this.accountAuthorities = new ArrayList<>(); }

    public Authority(String ime) {
        this.ime = ime;
        this.accountAuthorities = new ArrayList<>();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public List<AccountAuthority> getAccountAuthorities() { return accountAuthorities; }

    public void setAccountAuthorities(List<AccountAuthority> accountAuthorities) { this.accountAuthorities = accountAuthorities; }
}
