package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pacijent_bolest")
public class PacijentBolest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Bolest bolest;

    @Column(nullable = false)
    private Date datum_uspostavljanja_dijagnoze;

    @ManyToOne
    private Simptomi simptomi;

    @OneToMany(mappedBy = "pacijent_bolest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentBolestLek> lekovi_terapija;

    public PacijentBolest(){
        this.lekovi_terapija = new ArrayList<>();
    }

    public PacijentBolest(Pacijent pacijent, Bolest bolest, Date datum_uspostavljanja_dijagnoze, Simptomi simptomi,
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

    public Simptomi getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(Simptomi simptomi) {
        this.simptomi = simptomi;
    }

    public List<PacijentBolestLek> getLekovi_terapija() {
        return lekovi_terapija;
    }

    public void setLekovi_terapija(List<PacijentBolestLek> lekovi_terapija) {
        this.lekovi_terapija = lekovi_terapija;
    }
}
