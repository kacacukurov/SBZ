package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;

@Entity
@Table(name = "account_authority")
public class AccountAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Authority authority;

    public AccountAuthority() { }

    public AccountAuthority(Account account, Authority authority)
    {
        this.account = account;
        this.authority = authority;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }

    public Authority getAuthority() { return authority; }

    public void setAuthority(Authority authority) { this.authority = authority; }
}
