package rs.uns.ac.ftn.SBZprojekat.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "simptomi")
@Where(clause="deleted=0")
public class Simptomi {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = true)
    private Double vrednost;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Simptomi(){
    }

    public Simptomi(String naziv, Double vrednost) {
        this.naziv = naziv;
        this.vrednost = vrednost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getVrednost() {
        return vrednost;
    }

    public void setVrednost(Double vrednost) {
        this.vrednost = vrednost;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
