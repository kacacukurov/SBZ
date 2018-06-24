package rs.uns.ac.ftn.SBZprojekat.model.events;

import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;

public class PacijentNega {

    private String jmbg;
    private Dijagnoza dijagnoza;
    private int nivo_kiseonika;

    public PacijentNega() {
    }

    public PacijentNega(String jmbg, Dijagnoza dijagnoza, int nivo_kiseonika) {
        this.jmbg = jmbg;
        this.dijagnoza = dijagnoza;
        this.nivo_kiseonika = nivo_kiseonika;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Dijagnoza getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(Dijagnoza dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

    public int getNivo_kiseonika() {
        return nivo_kiseonika;
    }

    public void setNivo_kiseonika(int nivo_kiseonika) {
        this.nivo_kiseonika = nivo_kiseonika;
    }
}
