package rs.uns.ac.ftn.SBZprojekat.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("20m")
public class RastKiseonikaEvent implements Serializable {

    private String jmbg;
    private Double nivo;

    public RastKiseonikaEvent() {
        super();
    }

    public RastKiseonikaEvent(String jmbg, Double nivo) {
        this.jmbg = jmbg;
        this.nivo = nivo;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Double getNivo() {
        return nivo;
    }

    public void setNivo(Double nivo) {
        this.nivo = nivo;
    }
}
