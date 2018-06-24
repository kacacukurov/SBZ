package rs.uns.ac.ftn.SBZprojekat.monitoring;

import org.drools.core.ClassObjectFilter;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;
import rs.uns.ac.ftn.SBZprojekat.model.Bolest;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;
import rs.uns.ac.ftn.SBZprojekat.model.events.PacijentNega;
import rs.uns.ac.ftn.SBZprojekat.model.events.RastKiseonikaEvent;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProblemSaKiseonikomTest {

    @Test
    public void testCEPConfigThroughCode() {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource(
                "drools/spring/monitoring/monitoring.drl"));
        KieBuilder kbuilder = ks.newKieBuilder(kfs);
        kbuilder.buildAll();
        if (kbuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalArgumentException("Coudln't build knowledge module" + kbuilder.getResults());
        }
        KieContainer kContainer = ks.newKieContainer(kbuilder.getKieModule().getReleaseId());
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        kbconf.setOption(EventProcessingOption.STREAM);
        KieBase kbase = kContainer.newKieBase(kbconf);


        KieSessionConfiguration ksconf1 = ks.newKieSessionConfiguration();
        ksconf1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession ksession1 = kbase.newKieSession(ksconf1, null);

        KieSessionConfiguration ksconf2 = ks.newKieSessionConfiguration();
        ksconf2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession ksession2 = kbase.newKieSession(ksconf2, null);

    }

    @Test
    public void testCEPConfigThroughKModuleXML() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession2 = kContainer.newKieSession("kSessionPseudoClock");

        runPseudoClockExample(ksession2);
    }

    private void runPseudoClockExample(KieSession ksession) {
        String jmbg = "123456789";
        String jmbg2 = "12345";

        PacijentNega pacijentNega = new PacijentNega();
        pacijentNega.setJmbg(jmbg);
        pacijentNega.setNivo_kiseonika(20);
        SessionPseudoClock clock = ksession.getSessionClock();
        Dijagnoza dijagnoza = new Dijagnoza();
        Bolest bolest = new Bolest();
        bolest.setNazivBolesti("neka bolest");
        dijagnoza.setBolest(bolest);
        pacijentNega.setDijagnoza(dijagnoza);

        ksession.insert(pacijentNega);

        for (int index = 0; index < 30; index++) {
            RastKiseonikaEvent rastKiseonikaEvent = new RastKiseonikaEvent(jmbg, 1.0);
            ksession.insert(rastKiseonikaEvent);
            clock.advanceTime(1, TimeUnit.SECONDS);
            int ruleCount = ksession.fireAllRules();
            // nivo je manji od 70
            assertThat(ruleCount, equalTo(0));
        }

        // ovde sad vec 15 nema rasta, a nivo je manji od 70
        clock.advanceTime(15, TimeUnit.MINUTES);
        int ruleCount = ksession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        // drugi pacijent
        PacijentNega pacijentNega1 = new PacijentNega();
        pacijentNega1.setDijagnoza(dijagnoza);
        pacijentNega1.setJmbg(jmbg2);
        pacijentNega1.setNivo_kiseonika(80);

        for (int index = 0; index < 30; index++) {
            RastKiseonikaEvent rastKiseonikaEvent = new RastKiseonikaEvent(jmbg2, 1.0);
            ksession.insert(rastKiseonikaEvent);
            clock.advanceTime(1, TimeUnit.SECONDS);
            ruleCount = ksession.fireAllRules();
            // nivo je veci od 70
            assertThat(ruleCount, equalTo(0));
        }

        // ovde sad vec 15 nema rasta, a nivo je veci od 80
        clock.advanceTime(15, TimeUnit.MINUTES);
        ruleCount = ksession.fireAllRules();
        assertThat(ruleCount, equalTo(0));

        ksession.insert(pacijentNega1);

        Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(PacijentNega.class));
        assertThat(newEvents.size(), equalTo(2));
    }

}
