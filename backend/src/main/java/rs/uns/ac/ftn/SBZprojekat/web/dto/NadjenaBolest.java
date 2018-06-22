package rs.uns.ac.ftn.SBZprojekat.web.dto;

import rs.uns.ac.ftn.SBZprojekat.model.Bolest;

public class NadjenaBolest {

    private Bolest bolest;
    private int tezina;
    private int ukupno;

    public NadjenaBolest(){}

    public NadjenaBolest(Bolest bolest, int tezina, int ukupno) {
        this.bolest = bolest;
        this.tezina = tezina;
        this.ukupno = ukupno;
    }

    public Bolest getBolest() {
        return bolest;
    }

    public void setBolest(Bolest bolest) {
        this.bolest = bolest;
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
