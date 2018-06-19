package rs.uns.ac.ftn.SBZprojekat.model;

import rs.uns.ac.ftn.SBZprojekat.model.enumeration.NazivBolesti;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bolest")
public class Bolest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private NazivBolesti nazivBolesti;

    @OneToMany(mappedBy = "bolest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PacijentBolest> pacijentBolest;

    public Bolest(){
        this.pacijentBolest = new ArrayList<>();
    }

    public Bolest(NazivBolesti nazivBolesti, List<PacijentBolest> pacijentBolest) {
        this.nazivBolesti = nazivBolesti;
        this.pacijentBolest = pacijentBolest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NazivBolesti getNazivBolesti() {
        return nazivBolesti;
    }

    public void setNazivBolesti(NazivBolesti nazivBolesti) {
        this.nazivBolesti = nazivBolesti;
    }

    public List<PacijentBolest> getPacijentBolest() {
        return pacijentBolest;
    }

    public void setPacijentBolest(List<PacijentBolest> pacijentBolest) {
        this.pacijentBolest = pacijentBolest;
    }
}
