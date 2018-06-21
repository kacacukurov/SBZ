package rs.uns.ac.ftn.SBZprojekat.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.SBZprojekat;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;
import rs.uns.ac.ftn.SBZprojekat.model.Pacijent;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;

import java.util.ArrayList;
import java.util.List;


@Service
public class DroolsService {

    private static Logger log = LoggerFactory.getLogger(SBZprojekat.class);

    private final KieContainer kieContainer;


    @Autowired
    public DroolsService(KieContainer kieContainer) {
        log.info("Initialising a new example session.");
        this.kieContainer = kieContainer;
    }

    public void pokreniDrools() {
        KieSession kieSession = kieContainer.newKieSession("ExampleSession");
        Pacijent pacijent = new Pacijent();
        List<Dijagnoza> dijagnozas = new ArrayList<>();
        Dijagnoza dijagnoza = new Dijagnoza();
        dijagnoza.setPacijent(pacijent);
        List<Simptomi> simptomis = new ArrayList<>();
        Simptomi s1 = new Simptomi("zamor", null);
        Simptomi s2 = new Simptomi("nocturia", null);
        Simptomi s3 = new Simptomi("otoci nogu i zglobova", null);
        Simptomi s4 = new Simptomi("gusenje", null);
        Simptomi s5 = new Simptomi("boluje od dijabetesa", null);
        Simptomi s6 = new Simptomi("boluje od hipertenzije", null);
        Simptomi s7 = new Simptomi("oporavak", null);
        simptomis.add(s1);
        simptomis.add(s2);
        simptomis.add(s3);
        simptomis.add(s4);
        simptomis.add(s5);
        simptomis.add(s6);
        simptomis.add(s7);
        dijagnoza.setSimptomi(simptomis);
        pacijent.getDijagnoze().add(dijagnoza);

        kieSession.insert(dijagnoza);

        System.out.println(kieSession.fireAllRules());
        kieSession.dispose();

    }


}
