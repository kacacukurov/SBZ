package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PrepisaniLekoviDTO {

    private Long id_dijagnoze;
    private List<NoviLekDTO> lekovi;

    public PrepisaniLekoviDTO() {
        this.lekovi = new ArrayList<>();
    }

    public PrepisaniLekoviDTO(Long id_dijagnoze, List<NoviLekDTO> lekovi) {
        this.id_dijagnoze = id_dijagnoze;
        this.lekovi = lekovi;
    }

    public Long getId_dijagnoze() {
        return id_dijagnoze;
    }

    public void setId_dijagnoze(Long id_dijagnoze) {
        this.id_dijagnoze = id_dijagnoze;
    }

    public List<NoviLekDTO> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<NoviLekDTO> lekovi) {
        this.lekovi = lekovi;
    }
}
