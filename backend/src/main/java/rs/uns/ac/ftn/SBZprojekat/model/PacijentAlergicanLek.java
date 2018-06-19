package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;

@Entity
@Table(name = "pacijent_alergican_lek")
public class PacijentAlergicanLek {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Lek lek;

    public PacijentAlergicanLek(){
    }

    public PacijentAlergicanLek(Pacijent pacijent, Lek lek) {
        this.pacijent = pacijent;
        this.lek = lek;
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

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }
}
