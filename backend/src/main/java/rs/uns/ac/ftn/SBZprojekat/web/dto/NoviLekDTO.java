package rs.uns.ac.ftn.SBZprojekat.web.dto;

import rs.uns.ac.ftn.SBZprojekat.model.enumeration.TipLeka;

import java.util.ArrayList;
import java.util.List;

public class NoviLekDTO {

    private String naziv;
    private List<String> sastojci;
    private TipLeka tipLeka;

    public NoviLekDTO(){
        this.sastojci = new ArrayList<>();
    }

    public NoviLekDTO(String naziv, List<String> sastojci, TipLeka tipLeka) {
        this.naziv = naziv;
        this.sastojci = sastojci;
        this.tipLeka = tipLeka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<String> getSastojci() {
        return sastojci;
    }

    public void setSastojci(List<String> sastojci) {
        this.sastojci = sastojci;
    }

    public TipLeka getTipLeka() {
        return tipLeka;
    }

    public void setTipLeka(TipLeka tipLeka) {
        this.tipLeka = tipLeka;
    }
}
