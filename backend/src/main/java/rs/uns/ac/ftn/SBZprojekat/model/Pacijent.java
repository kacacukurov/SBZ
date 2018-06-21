package rs.uns.ac.ftn.SBZprojekat.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacijent")
@Where(clause="deleted=0")
public class Pacijent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    private String jmbg;

    @Column(nullable = false)
    private String broj_zdravstvene_knjizice;

    @OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Dijagnoza> dijagnoze;

    @ManyToMany
    private List<Lek> lekovi_alergija;

    @ManyToMany
    private List<Sastojak> sastojci_alergija;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Pacijent(){
        this.dijagnoze = new ArrayList<>();
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }


    public Pacijent(String ime, String prezime, String jmbg, String broj_zdravstvene_knjizice) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_zdravstvene_knjizice = broj_zdravstvene_knjizice;
        this.dijagnoze = new ArrayList<>();
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }

    public Pacijent(String ime, String prezime, String jmbg, String broj_zdravstvene_knjizice, List<Dijagnoza>
            bolesti_pacijenta, List<Lek> lekovi_alergija, List<Sastojak> sastojci_alergija) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_zdravstvene_knjizice = broj_zdravstvene_knjizice;
        this.dijagnoze = bolesti_pacijenta;
        this.lekovi_alergija = lekovi_alergija;
        this.sastojci_alergija = sastojci_alergija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBroj_zdravstvene_knjizice() {
        return broj_zdravstvene_knjizice;
    }

    public void setBroj_zdravstvene_knjizice(String broj_zdravstvene_knjizice) {
        this.broj_zdravstvene_knjizice = broj_zdravstvene_knjizice;
    }

    public List<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public List<Lek> getLekovi_alergija() {
        return lekovi_alergija;
    }

    public void setLekovi_alergija(List<Lek> lekovi_alergija) {
        this.lekovi_alergija = lekovi_alergija;
    }

    public List<Sastojak> getSastojci_alergija() {
        return sastojci_alergija;
    }

    public void setSastojci_alergija(List<Sastojak> sastojci_alergija) {
        this.sastojci_alergija = sastojci_alergija;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
