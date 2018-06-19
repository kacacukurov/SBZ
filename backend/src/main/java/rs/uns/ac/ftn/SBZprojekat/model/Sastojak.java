package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;

@Entity
@Table(name = "sastojak")
public class Sastojak {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @ManyToOne
    private Lek lek;

    @ManyToOne
    private Pacijent pacijent;

    public Sastojak(){}

    public Sastojak(String naziv, Lek lek, Pacijent pacijent) {
        this.naziv = naziv;
        this.lek = lek;
        this.pacijent = pacijent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }
}
