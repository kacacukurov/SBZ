package rs.uns.ac.ftn.SBZprojekat.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "sastojak")
@Where(clause="deleted=0")
public class Sastojak {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @ManyToOne
    private Pacijent pacijent;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Sastojak(){}

    public Sastojak(String naziv){
        this.naziv = naziv;
    }

    public Sastojak(String naziv, Pacijent pacijent) {
        this.naziv = naziv;
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

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
