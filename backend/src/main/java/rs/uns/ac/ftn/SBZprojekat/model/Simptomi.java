package rs.uns.ac.ftn.SBZprojekat.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "simptomi")
public class Simptomi {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "simptomi")
    private List<PacijentBolest> pacijent_bolest;

    @Column(nullable = false)
    private boolean curenje_iz_nosa;

    @Column(nullable = false)
    private boolean bol_u_grlu;

    @Column(nullable = false)
    private boolean glavobolja;

    @Column(nullable = false)
    private boolean kijanje;

    @Column(nullable = false)
    private boolean kasalj;

    @Column(nullable = false)
    private boolean temp_veca_38;

    @Column(nullable = false)
    private boolean drhtavica;

    @Column(nullable = false)
    private boolean bol_do_usiju;

    @Column(nullable = false)
    private boolean temp_40_41;

    @Column(nullable = false)
    private boolean gubitak_apetita;

    @Column(nullable = false)
    private boolean umor;

    @Column(nullable = false)
    private boolean zuti_sekret_iz_nosa;

    @Column(nullable = false)
    private boolean oticanje_oko_ociju;

    @Column(nullable = false)
    private boolean cesto_uriniranje;

    @Column(nullable = false)
    private boolean gubitak_tezine;

    @Column(nullable = false)
    private boolean mucnina_povracanje;

    @Column(nullable = false)
    private boolean nocturia;

    @Column(nullable = false)
    private boolean otoci_nogu_zglobova;

    @Column(nullable = false)
    private boolean gusenje;

    @Column(nullable = false)
    private boolean bol_u_grudima;

    @Column(nullable = false)
    private boolean dijareja;

    public Simptomi(){
        this.pacijent_bolest = new ArrayList<>();
    }

    public Simptomi(boolean curenje_iz_nosa, boolean bol_u_grlu, boolean glavobolja, boolean kijanje, boolean kasalj,
                    boolean temp_veca_38, boolean drhtavica, boolean bol_do_usiju, boolean temp_40_41, boolean gubitak_apetita,
                    boolean umor, boolean zuti_sekret_iz_nosa, boolean oticanje_oko_ociju, boolean cesto_uriniranje,
                    boolean gubitak_tezine, boolean mucnina_povracanje, boolean nocturia, boolean otoci_nogu_zglobova,
                    boolean gusenje, boolean bol_u_grudima, boolean dijareja) {
        this.curenje_iz_nosa = curenje_iz_nosa;
        this.bol_u_grlu = bol_u_grlu;
        this.glavobolja = glavobolja;
        this.kijanje = kijanje;
        this.kasalj = kasalj;
        this.temp_veca_38 = temp_veca_38;
        this.drhtavica = drhtavica;
        this.bol_do_usiju = bol_do_usiju;
        this.temp_40_41 = temp_40_41;
        this.gubitak_apetita = gubitak_apetita;
        this.umor = umor;
        this.zuti_sekret_iz_nosa = zuti_sekret_iz_nosa;
        this.oticanje_oko_ociju = oticanje_oko_ociju;
        this.cesto_uriniranje = cesto_uriniranje;
        this.gubitak_tezine = gubitak_tezine;
        this.mucnina_povracanje = mucnina_povracanje;
        this.nocturia = nocturia;
        this.otoci_nogu_zglobova = otoci_nogu_zglobova;
        this.gusenje = gusenje;
        this.bol_u_grudima = bol_u_grudima;
        this.dijareja = dijareja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PacijentBolest> getPacijent_bolest() {
        return pacijent_bolest;
    }

    public void setPacijent_bolest(List<PacijentBolest> pacijent_bolest) {
        this.pacijent_bolest = pacijent_bolest;
    }

    public boolean isCurenje_iz_nosa() {
        return curenje_iz_nosa;
    }

    public void setCurenje_iz_nosa(boolean curenje_iz_nosa) {
        this.curenje_iz_nosa = curenje_iz_nosa;
    }

    public boolean isBol_u_grlu() {
        return bol_u_grlu;
    }

    public void setBol_u_grlu(boolean bol_u_grlu) {
        this.bol_u_grlu = bol_u_grlu;
    }

    public boolean isGlavobolja() {
        return glavobolja;
    }

    public void setGlavobolja(boolean glavobolja) {
        this.glavobolja = glavobolja;
    }

    public boolean isKijanje() {
        return kijanje;
    }

    public void setKijanje(boolean kijanje) {
        this.kijanje = kijanje;
    }

    public boolean isKasalj() {
        return kasalj;
    }

    public void setKasalj(boolean kasalj) {
        this.kasalj = kasalj;
    }

    public boolean isTemp_veca_38() {
        return temp_veca_38;
    }

    public void setTemp_veca_38(boolean temp_veca_38) {
        this.temp_veca_38 = temp_veca_38;
    }

    public boolean isDrhtavica() {
        return drhtavica;
    }

    public void setDrhtavica(boolean drhtavica) {
        this.drhtavica = drhtavica;
    }

    public boolean isBol_do_usiju() {
        return bol_do_usiju;
    }

    public void setBol_do_usiju(boolean bol_do_usiju) {
        this.bol_do_usiju = bol_do_usiju;
    }

    public boolean isTemp_40_41() {
        return temp_40_41;
    }

    public void setTemp_40_41(boolean temp_40_41) {
        this.temp_40_41 = temp_40_41;
    }

    public boolean isGubitak_apetita() {
        return gubitak_apetita;
    }

    public void setGubitak_apetita(boolean gubitak_apetita) {
        this.gubitak_apetita = gubitak_apetita;
    }

    public boolean isUmor() {
        return umor;
    }

    public void setUmor(boolean umor) {
        this.umor = umor;
    }

    public boolean isZuti_sekret_iz_nosa() {
        return zuti_sekret_iz_nosa;
    }

    public void setZuti_sekret_iz_nosa(boolean zuti_sekret_iz_nosa) {
        this.zuti_sekret_iz_nosa = zuti_sekret_iz_nosa;
    }

    public boolean isOticanje_oko_ociju() {
        return oticanje_oko_ociju;
    }

    public void setOticanje_oko_ociju(boolean oticanje_oko_ociju) {
        this.oticanje_oko_ociju = oticanje_oko_ociju;
    }

    public boolean isCesto_uriniranje() {
        return cesto_uriniranje;
    }

    public void setCesto_uriniranje(boolean cesto_uriniranje) {
        this.cesto_uriniranje = cesto_uriniranje;
    }

    public boolean isGubitak_tezine() {
        return gubitak_tezine;
    }

    public void setGubitak_tezine(boolean gubitak_tezine) {
        this.gubitak_tezine = gubitak_tezine;
    }

    public boolean isMucnina_povracanje() {
        return mucnina_povracanje;
    }

    public void setMucnina_povracanje(boolean mucnina_povracanje) {
        this.mucnina_povracanje = mucnina_povracanje;
    }

    public boolean isNocturia() {
        return nocturia;
    }

    public void setNocturia(boolean nocturia) {
        this.nocturia = nocturia;
    }

    public boolean isOtoci_nogu_zglobova() {
        return otoci_nogu_zglobova;
    }

    public void setOtoci_nogu_zglobova(boolean otoci_nogu_zglobova) {
        this.otoci_nogu_zglobova = otoci_nogu_zglobova;
    }

    public boolean isGusenje() {
        return gusenje;
    }

    public void setGusenje(boolean gusenje) {
        this.gusenje = gusenje;
    }

    public boolean isBol_u_grudima() {
        return bol_u_grudima;
    }

    public void setBol_u_grudima(boolean bol_u_grudima) {
        this.bol_u_grudima = bol_u_grudima;
    }

    public boolean isDijareja() {
        return dijareja;
    }

    public void setDijareja(boolean dijareja) {
        this.dijareja = dijareja;
    }
}
