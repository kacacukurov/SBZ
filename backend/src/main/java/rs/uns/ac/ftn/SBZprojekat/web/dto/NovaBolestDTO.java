package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class NovaBolestDTO {

    private String naziv;
    private List<SimptomDTO> opsti;
    private List<SimptomDTO> specificni;

    public NovaBolestDTO(){
        this.opsti = new ArrayList<>();
        this.specificni = new ArrayList<>();
    }

    public NovaBolestDTO(String naziv, List<SimptomDTO> opsti, List<SimptomDTO> specificni) {
        this.naziv = naziv;
        this.opsti = opsti;
        this.specificni = specificni;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<SimptomDTO> getOpsti() {
        return opsti;
    }

    public void setOpsti(List<SimptomDTO> opsti) {
        this.opsti = opsti;
    }

    public List<SimptomDTO> getSpecificni() {
        return specificni;
    }

    public void setSpecificni(List<SimptomDTO> specificni) {
        this.specificni = specificni;
    }
}
