package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class SpisakPacijenataDTO {

    private List<PacijentOsnovnoDTO> pacijenti;

    public SpisakPacijenataDTO() {
        this.pacijenti = new ArrayList<>();
    }

    public SpisakPacijenataDTO(List<PacijentOsnovnoDTO> pacijenti) {
        this.pacijenti = pacijenti;
    }

    public List<PacijentOsnovnoDTO> getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(List<PacijentOsnovnoDTO> pacijenti) {
        this.pacijenti = pacijenti;
    }
}
