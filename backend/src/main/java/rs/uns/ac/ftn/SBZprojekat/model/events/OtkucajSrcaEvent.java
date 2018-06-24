package rs.uns.ac.ftn.SBZprojekat.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("10m")
public class OtkucajSrcaEvent implements Serializable{

    private String jmbg;

    public OtkucajSrcaEvent() {
        super();
    }

    public OtkucajSrcaEvent(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
