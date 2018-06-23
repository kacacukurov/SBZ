package rs.uns.ac.ftn.SBZprojekat.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.SBZprojekat;
import rs.uns.ac.ftn.SBZprojekat.model.*;
import rs.uns.ac.ftn.SBZprojekat.web.dto.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;


@Service
public class DroolsService {

    private static Logger log = LoggerFactory.getLogger(SBZprojekat.class);


    private final KieSession kieSession;

    @Autowired
    private BolestService bolestService;

    @Autowired
    private DijagnozaService dijagnozaService;

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private LekService lekService;

    @Autowired
    private SastojakService sastojakService;

    @Autowired
    private SimptomiService simptomiService;


    @Autowired
    public DroolsService(KieSession kieSession) {
        this.kieSession = kieSession;

    }

    public Dijagnoza dobaviNajverovatnijuBolest(Dijagnoza dijagnoza){

        kieSession.insert(dijagnoza);
        kieSession.insert(dijagnoza.getPacijent());

        kieSession.getAgenda().getAgendaGroup("bolesti").setFocus();
        kieSession.insert(this.bolestService);
        kieSession.insert(this.dijagnozaService);
        List<Simptomi> simptomis = this.simptomiService.findAll();
        for(Simptomi s: simptomis)
            kieSession.insert(s);

        System.out.println(kieSession.fireAllRules());

        kieSession.destroy();

        return dijagnoza;
    }

    public NovaBolestDTO dobaviSimptome(String naziv_bolesti){

        NovaBolestDTO novaBolestDTO = new NovaBolestDTO();
        novaBolestDTO.setNaziv(naziv_bolesti);
        kieSession.insert(novaBolestDTO);
        kieSession.insert(this.simptomiService);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.destroy();
        return novaBolestDTO;
    }

    public ListaBolestiDTO dobaviMoguceBolesti(Dijagnoza dijagnoza){

        kieSession.insert(dijagnoza);
        ListaBolestiDTO listaBolestiDTO = new ListaBolestiDTO();

        kieSession.getAgenda().getAgendaGroup("bolesti").setFocus();
        kieSession.insert(this.bolestService);
        kieSession.insert(this.dijagnozaService);
        List<Simptomi> simptomis = this.simptomiService.findAll();
        for(Simptomi s: simptomis)
            kieSession.insert(s);

        kieSession.insert(listaBolestiDTO);
        System.out.println(kieSession.fireAllRules());

        kieSession.destroy();

        return listaBolestiDTO;
    }

    public SpisakAlergijaDTO proveriAlergije(Dijagnoza dijagnoza){
        SpisakAlergijaDTO alergije = new SpisakAlergijaDTO();

        Pacijent pacijent = dijagnoza.getPacijent();

        kieSession.insert(dijagnoza);
        kieSession.insert(pacijent);
        kieSession.insert(alergije);
        kieSession.getAgenda().getAgendaGroup("alergije").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.destroy();

        return alergije;
    }

    public SpisakPacijenataDTO pacijentiHronicno(){

        SpisakPacijenataDTO spisakPacijenataDTO = new SpisakPacijenataDTO();

        List<Pacijent> pacijents = this.pacijentService.findAll();
        for(Pacijent pacijent : pacijents)
            kieSession.insert(pacijent);
        List<Bolest> bolesti = this.bolestService.findAll();
        for(Bolest bolest: bolesti)
            kieSession.insert(bolest);

        kieSession.insert(spisakPacijenataDTO);
        kieSession.getAgenda().getAgendaGroup("hronicne").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.destroy();


        return spisakPacijenataDTO;
    }

    public SpisakPacijenataDTO pacijetniZavisnici(){

        SpisakPacijenataDTO spisakPacijenataDTO = new SpisakPacijenataDTO();
        List<Pacijent> pacijents = this.pacijentService.findAll();
        for(Pacijent pacijent : pacijents){
            kieSession.insert(pacijent);
        }

        List<Bolest> bolesti = this.bolestService.findAll();
        for(Bolest bolest: bolesti)
            kieSession.insert(bolest);
        List<Lek> lekovi = this.lekService.findAll();
        for(Lek lek: lekovi)
            kieSession.insert(lek);

        kieSession.insert(spisakPacijenataDTO);
        kieSession.getAgenda().getAgendaGroup("zavisnici").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.destroy();

        return spisakPacijenataDTO;
    }

    public SpisakPacijenataDTO pacijentiSlabImunitet(){

        SpisakPacijenataDTO spisakPacijenataDTO = new SpisakPacijenataDTO();

        List<Pacijent> pacijents = this.pacijentService.findAll();
        for(Pacijent pacijent : pacijents){
            kieSession.insert(pacijent);
            System.out.println(pacijent.getIme());
        }

        kieSession.insert(spisakPacijenataDTO);
        kieSession.getAgenda().getAgendaGroup("imunitet").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.destroy();

        return spisakPacijenataDTO;
    }

}
