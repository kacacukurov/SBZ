package rs.uns.ac.ftn.SBZprojekat.model.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class DijalizaEvent {

    private String jmbg;

    public DijalizaEvent() {
    }

    public DijalizaEvent(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
