package rs.uns.ac.ftn.SBZprojekat.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.SBZprojekat;
import rs.uns.ac.ftn.SBZprojekat.model.*;

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

        dijagnoza = this.dijagnozaService.findOne(dijagnoza.getId());

        kieSession.destroy();

        return dijagnoza;
    }


}
