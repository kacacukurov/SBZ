package rs.uns.ac.ftn.SBZprojekat.model.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class UbrzanSrcaniRitamEvent {

    private String jmbg;

    public UbrzanSrcaniRitamEvent() {
    }

    public UbrzanSrcaniRitamEvent(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
