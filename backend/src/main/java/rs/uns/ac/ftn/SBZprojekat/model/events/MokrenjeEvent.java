package rs.uns.ac.ftn.SBZprojekat.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("20m")
public class MokrenjeEvent implements Serializable{

    private String jmbg;

    private int kolicina;

    public MokrenjeEvent(){
        super();
    }

    public MokrenjeEvent(String jmbg, int kolicina) {
        this.jmbg = jmbg;
        this.kolicina = kolicina;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
