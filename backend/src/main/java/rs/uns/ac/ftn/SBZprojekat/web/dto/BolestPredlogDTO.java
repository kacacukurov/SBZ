package rs.uns.ac.ftn.SBZprojekat.web.dto;

public class BolestPredlogDTO {

    private String naziv_bolesto;
    private int tezina;
    private int ukupno;

    public BolestPredlogDTO() {
    }

    public BolestPredlogDTO(String naziv_bolesto, int tezina, int ukupno) {
        this.naziv_bolesto = naziv_bolesto;
        this.tezina = tezina;
        this.ukupno = ukupno;
    }

    public String getNaziv_bolesto() {
        return naziv_bolesto;
    }

    public void setNaziv_bolesto(String naziv_bolesto) {
        this.naziv_bolesto = naziv_bolesto;
    }

    public int getTezina() {
        return tezina;
    }

    public void setTezina(int tezina) {
        this.tezina = tezina;
    }

    public int getUkupno() {
        return ukupno;
    }

    public void setUkupno(int ukupno) {
        this.ukupno = ukupno;
    }
}
