package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class DijagnozaDTO {

    private String naziv_bolesti;
    private String jmbg;
    private String datum;
    private List<SimptomDTO> simptomi;
    private Long id;

    public DijagnozaDTO(){
        this.simptomi = new ArrayList<>();
    }

    public DijagnozaDTO(String naziv_bolesti, String jmbg, String datum, Long id) {
        this.naziv_bolesti = naziv_bolesti;
        this.jmbg = jmbg;
        this.simptomi = new ArrayList<>();
        this.datum = datum;
        this.id = id;
    }

    public DijagnozaDTO(String naziv_bolesti, String jmbg, List<SimptomDTO> simptomi) {
        this.naziv_bolesti = naziv_bolesti;
        this.jmbg = jmbg;
        this.simptomi = simptomi;
    }

    public String getNaziv_bolesti() {
        return naziv_bolesti;
    }

    public void setNaziv_bolesti(String naziv_bolesti) {
        this.naziv_bolesti = naziv_bolesti;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public List<SimptomDTO> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(List<SimptomDTO> simptomi) {
        this.simptomi = simptomi;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
