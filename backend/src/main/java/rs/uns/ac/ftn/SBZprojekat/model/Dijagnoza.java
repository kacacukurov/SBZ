package rs.uns.ac.ftn.SBZprojekat.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dijagnoza")
public class Dijagnoza {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Bolest bolest;

    @Column(nullable = false)
    private Date datum_uspostavljanja_dijagnoze;

    @ManyToMany
    private List<Simptomi> simptomi;

    @OneToMany(mappedBy = "dijagnoza", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentBolestLek> lekovi_terapija;

    public Dijagnoza(){
        this.simptomi = new ArrayList<>();
        this.lekovi_terapija = new ArrayList<>();
    }

    public Dijagnoza(Pacijent pacijent, Bolest bolest, Date datum_uspostavljanja_dijagnoze, List<Simptomi> simptomi,
                     List<PacijentBolestLek> lekovi_terapija) {
        this.pacijent = pacijent;
        this.bolest = bolest;
        this.datum_uspostavljanja_dijagnoze = datum_uspostavljanja_dijagnoze;
        this.simptomi = simptomi;
        this.lekovi_terapija = lekovi_terapija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Bolest getBolest() {
        return bolest;
    }

    public void setBolest(Bolest bolest) {
        this.bolest = bolest;
    }

    public Date getDatum_uspostavljanja_dijagnoze() {
        return datum_uspostavljanja_dijagnoze;
    }

    public void setDatum_uspostavljanja_dijagnoze(Date datum_uspostavljanja_dijagnoze) {
        this.datum_uspostavljanja_dijagnoze = datum_uspostavljanja_dijagnoze;
    }

    public List<Simptomi> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(List<Simptomi> simptomi) {
        this.simptomi = simptomi;
    }

    public List<PacijentBolestLek> getLekovi_terapija() {
        return lekovi_terapija;
    }

    public void setLekovi_terapija(List<PacijentBolestLek> lekovi_terapija) {
        this.lekovi_terapija = lekovi_terapija;
    }
}
