package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class NoviPacijentDTO {

    private String ime;
    private String prezime;
    private String jmbg;
    private String broj_knjizice;
    private List<String> lekovi_alergija;
    private List<String> sastojci_alergija;

    public NoviPacijentDTO(){
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }

    public NoviPacijentDTO(String ime, String prezime, String jmbg, String broj_knjizice) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_knjizice = broj_knjizice;
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBroj_knjizice() {
        return broj_knjizice;
    }

    public void setBroj_knjizice(String broj_knjizice) {
        this.broj_knjizice = broj_knjizice;
    }

    public List<String> getLekovi_alergija() {
        return lekovi_alergija;
    }

    public void setLekovi_alergija(List<String> lekovi_alergija) {
        this.lekovi_alergija = lekovi_alergija;
    }

    public List<String> getSastojci_alergija() {
        return sastojci_alergija;
    }

    public void setSastojci_alergija(List<String> sastojci_alergija) {
        this.sastojci_alergija = sastojci_alergija;
    }
}
