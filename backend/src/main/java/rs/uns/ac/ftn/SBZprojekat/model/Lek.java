package rs.uns.ac.ftn.SBZprojekat.model;

import rs.uns.ac.ftn.SBZprojekat.model.enumeration.TipLeka;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lek")
public class Lek {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private TipLeka tipLeka;

    @Column(nullable = false)
    private String naziv_leka;

    @OneToMany(mappedBy = "lek", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Sastojak> sastojci;

    @OneToMany(mappedBy = "lek", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentAlergicanLek> alergicni_pacijenti;

    @OneToMany(mappedBy = "lek", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentBolestLek> lekovi_pacijenata;

    public Lek(){
        this.sastojci = new ArrayList<>();
        this.alergicni_pacijenti = new ArrayList<>();
        this.lekovi_pacijenata = new ArrayList<>();
    }

    public Lek(TipLeka tipLeka, String naziv_leka, List<Sastojak> sastojci, List<PacijentAlergicanLek> alergicni_pacijenti,
               List<PacijentBolestLek> lekovi_pacijenata) {
        this.tipLeka = tipLeka;
        this.naziv_leka = naziv_leka;
        this.sastojci = sastojci;
        this.alergicni_pacijenti = alergicni_pacijenti;
        this.lekovi_pacijenata = lekovi_pacijenata;
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

    public String getNaziv_leka() {
        return naziv_leka;
    }

    public void setNaziv_leka(String naziv_leka) {
        this.naziv_leka = naziv_leka;
    }

    public List<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(List<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }

    public List<PacijentAlergicanLek> getAlergicni_pacijenti() {
        return alergicni_pacijenti;
    }

    public void setAlergicni_pacijenti(List<PacijentAlergicanLek> alergicni_pacijenti) {
        this.alergicni_pacijenti = alergicni_pacijenti;
    }

    public List<PacijentBolestLek> getLekovi_pacijenata() {
        return lekovi_pacijenata;
    }

    public void setLekovi_pacijenata(List<PacijentBolestLek> lekovi_pacijenata) {
        this.lekovi_pacijenata = lekovi_pacijenata;
    }
}
