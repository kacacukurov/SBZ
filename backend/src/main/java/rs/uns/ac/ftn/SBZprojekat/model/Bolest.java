package rs.uns.ac.ftn.SBZprojekat.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bolest")
@Where(clause="deleted=0")
public class Bolest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String nazivBolesti;

    @ManyToMany
    private List<Simptomi> opsti_simptomi;

    @ManyToMany
    private List<Simptomi> specificni_simptomi;

    @OneToMany(mappedBy = "bolest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Dijagnoza> dijagnoze;


    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Bolest(){
        this.opsti_simptomi = new ArrayList<>();
        this.specificni_simptomi = new ArrayList<>();
        this.dijagnoze = new ArrayList<>();
    }

    public Bolest(String nazivBolesti, List<Simptomi> opsti_simptomi, List<Simptomi> specificni_simptomi, List<Dijagnoza> dijagnoze) {
        this.nazivBolesti = nazivBolesti;
        this.opsti_simptomi = opsti_simptomi;
        this.specificni_simptomi = specificni_simptomi;
        this.dijagnoze = dijagnoze;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivBolesti() {
        return nazivBolesti;
    }

    public void setNazivBolesti(String nazivBolesti) {
        this.nazivBolesti = nazivBolesti;
    }

    public List<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public List<Simptomi> getOpsti_simptomi() {
        return opsti_simptomi;
    }

    public void setOpsti_simptomi(List<Simptomi> opsti_simptomi) {
        this.opsti_simptomi = opsti_simptomi;
    }

    public List<Simptomi> getSpecificni_simptomi() {
        return specificni_simptomi;
    }

    public void setSpecificni_simptomi(List<Simptomi> specificni_simptomi) {
        this.specificni_simptomi = specificni_simptomi;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
