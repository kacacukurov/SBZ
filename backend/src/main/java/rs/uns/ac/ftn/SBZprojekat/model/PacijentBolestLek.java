package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;

@Entity
@Table(name = "pacijent_bolest_lek")
public class PacijentBolestLek {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Dijagnoza dijagnoza;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Lek lek;

    public PacijentBolestLek(){}

    public PacijentBolestLek(Dijagnoza pacijent_bolest, Lek lek) {
        this.dijagnoza = pacijent_bolest;
        this.lek = lek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dijagnoza getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(Dijagnoza dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }
}
