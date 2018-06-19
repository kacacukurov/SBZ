package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;

@Entity
@Table(name = "pacijent_bolest_lek")
public class PacijentBolestLek {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private PacijentBolest pacijent_bolest;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Lek lek;

    public PacijentBolestLek(){}

    public PacijentBolestLek(PacijentBolest pacijent_bolest, Lek lek) {
        this.pacijent_bolest = pacijent_bolest;
        this.lek = lek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacijentBolest getPacijent_bolest() {
        return pacijent_bolest;
    }

    public void setPacijent_bolest(PacijentBolest pacijent_bolest) {
        this.pacijent_bolest = pacijent_bolest;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }
}
