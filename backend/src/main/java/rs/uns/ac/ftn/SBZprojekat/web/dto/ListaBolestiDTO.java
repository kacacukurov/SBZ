package rs.uns.ac.ftn.SBZprojekat.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ListaBolestiDTO {
    private List<BolestPredlogDTO> bolesti;

    public ListaBolestiDTO() {
        this.bolesti = new ArrayList<>();
    }

    public ListaBolestiDTO(List<BolestPredlogDTO> bolesti) {
        this.bolesti = bolesti;
    }

    public List<BolestPredlogDTO> getBolesti() {
        return bolesti;
    }

    public void setBolesti(List<BolestPredlogDTO> bolesti) {
        this.bolesti = bolesti;
    }
}
