package rs.uns.ac.ftn.SBZprojekat.model;

import org.hibernate.annotations.Where;
import rs.uns.ac.ftn.SBZprojekat.model.enumeration.TipLeka;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lek")
@Where(clause="deleted=0")
public class Lek {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private TipLeka tipLeka;

    @Column(nullable = false)
    private String naziv;

    @ManyToMany
    private List<Sastojak> sastojci;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Lek(){
        this.sastojci = new ArrayList<>();
    }

    public Lek(TipLeka tipLeka, String naziv_leka, List<Sastojak> sastojci) {
        this.tipLeka = tipLeka;
        this.naziv = naziv_leka;
        this.sastojci = sastojci;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipLeka getTipLeka() {
        return tipLeka;
    }

    public void setTipLeka(TipLeka tipLeka) {
        this.tipLeka = tipLeka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(List<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
