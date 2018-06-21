package rs.uns.ac.ftn.SBZprojekat.web.dto;

public class NoviPacijentDTO {

    private String ime;

    private String prezime;

    private String jmbg;

    private String broj_knjizice;

    public NoviPacijentDTO(){}

    public NoviPacijentDTO(String ime, String prezime, String jmbg, String broj_knjizice) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_knjizice = broj_knjizice;
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
}
