package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PacijentDTO {

    private NoviPacijentDTO pacijentDTO;
    private List<DijagnozaDTO> dijagnoze;
    private List<String> lekovi_alergija;
    private List<String> sastojci_alergija;

    public PacijentDTO(){
        this.dijagnoze = new ArrayList<>();
        this.lekovi_alergija = new ArrayList<>();
        this.sastojci_alergija = new ArrayList<>();
    }

    public PacijentDTO(NoviPacijentDTO pacijentDTO, List<DijagnozaDTO> dijagnoze, List<String> lekovi_alergija, List<String> sastojci_alergija) {
        this.pacijentDTO = pacijentDTO;
        this.dijagnoze = dijagnoze;
        this.lekovi_alergija = lekovi_alergija;
        this.sastojci_alergija = sastojci_alergija;
    }

    public NoviPacijentDTO getPacijentDTO() {
        return pacijentDTO;
    }

    public void setPacijentDTO(NoviPacijentDTO pacijentDTO) {
        this.pacijentDTO = pacijentDTO;
    }

    public List<DijagnozaDTO> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<DijagnozaDTO> dijagnoze) {
        this.dijagnoze = dijagnoze;
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
