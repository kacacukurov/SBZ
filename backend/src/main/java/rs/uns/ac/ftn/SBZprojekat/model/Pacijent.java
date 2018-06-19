package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacijent")
public class Pacijent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    private Long jmbg;

    @Column(nullable = false)
    private Long broj_zdravstvene_knjizice;

    @OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentBolest> bolesti_pacijenta;

    @OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentAlergicanLek> lekovi_alergija;

    @OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Sastojak> sastojci_alergija;

    public Pacijent(){
        this.bolesti_pacijenta = new ArrayList<>();
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }


    public Pacijent(String ime, String prezime, Long jmbg, Long broj_zdravstvene_knjizice) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_zdravstvene_knjizice = broj_zdravstvene_knjizice;
        this.bolesti_pacijenta = new ArrayList<>();
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }

    public Pacijent(String ime, String prezime, Long jmbg, Long broj_zdravstvene_knjizice, List<PacijentBolest>
            bolesti_pacijenta, List<PacijentAlergicanLek> lekovi_alergija, List<Sastojak> sastojci_alergija) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_zdravstvene_knjizice = broj_zdravstvene_knjizice;
        this.bolesti_pacijenta = bolesti_pacijenta;
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

    public Long getJmbg() {
        return jmbg;
    }

    public void setJmbg(Long jmbg) {
        this.jmbg = jmbg;
    }

    public Long getBroj_zdravstvene_knjizice() {
        return broj_zdravstvene_knjizice;
    }

    public void setBroj_zdravstvene_knjizice(Long broj_zdravstvene_knjizice) {
        this.broj_zdravstvene_knjizice = broj_zdravstvene_knjizice;
    }

    public List<PacijentBolest> getBolesti_pacijenta() {
        return bolesti_pacijenta;
    }

    public void setBolesti_pacijenta(List<PacijentBolest> bolesti_pacijenta) {
        this.bolesti_pacijenta = bolesti_pacijenta;
    }

    public List<PacijentAlergicanLek> getLekovi_alergija() {
        return lekovi_alergija;
    }

    public void setLekovi_alergija(List<PacijentAlergicanLek> lekovi_alergija) {
        this.lekovi_alergija = lekovi_alergija;
    }

    public List<Sastojak> getSastojci_alergija() {
        return sastojci_alergija;
    }

    public void setSastojci_alergija(List<Sastojak> sastojci_alergija) {
        this.sastojci_alergija = sastojci_alergija;
    }
}
