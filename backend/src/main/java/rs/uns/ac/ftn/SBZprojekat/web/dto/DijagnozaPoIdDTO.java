package rs.uns.ac.ftn.SBZprojekat.web.dto;

import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;

import java.util.ArrayList;
import java.util.List;

public class DijagnozaPoIdDTO {

    private String naziv_bolesti;
    private String datum;
    private List<String> simptomi;
    private List<String> lekovi;

    public DijagnozaPoIdDTO(){
        this.simptomi = new ArrayList<>();
        this.lekovi = new ArrayList<>();
    }

    public DijagnozaPoIdDTO(String naziv_bolesti, String datum, List<String> simptomi, List<String> lekovi) {
        this.naziv_bolesti = naziv_bolesti;
        this.datum = datum;
        this.simptomi = simptomi;
        this.lekovi = lekovi;
    }

    public String getNaziv_bolesti() {
        return naziv_bolesti;
    }

    public void setNaziv_bolesti(String naziv_bolesti) {
        this.naziv_bolesti = naziv_bolesti;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public List<String> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(List<String> simptomi) {
        this.simptomi = simptomi;
    }

    public List<String> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<String> lekovi) {
        this.lekovi = lekovi;
    }
}
