package rs.uns.ac.ftn.SBZprojekat.web.dto;

public class SimptomDTO {

    private String naziv;

    private Double vrednost;

    public SimptomDTO(){}

    public SimptomDTO(String naziv, Double vrednost) {
        this.naziv = naziv;
        this.vrednost = vrednost;
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
}
