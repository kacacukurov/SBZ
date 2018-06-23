package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.HashSet;
import java.util.Set;

public class SpisakAlergijaDTO {

    Set<String> nazivi;

    public SpisakAlergijaDTO() {
        this.nazivi = new HashSet<>();
    }

    public SpisakAlergijaDTO(Set<String> nazivi) {
        this.nazivi = nazivi;
    }

    public Set<String> getNazivi() {
        return nazivi;
    }

    public void setNazivi(Set<String> nazivi) {
        this.nazivi = nazivi;
    }
}
