package rs.uns.ac.ftn.SBZprojekat.monitoring;

import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.uns.ac.ftn.SBZprojekat.model.Bolest;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;
import rs.uns.ac.ftn.SBZprojekat.model.events.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DijalizaTest {

    @Test
    public void testCEPConfigThroughKModuleXML() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession2 = kContainer.newKieSession("kSessionPseudoClock");

        runPseudoClockExample(ksession2);
    }

    private void runPseudoClockExample(KieSession ksession) {
        String jmbg = "123456789";

        PacijentNega pacijentNega = new PacijentNega();
        pacijentNega.setJmbg(jmbg);
        pacijentNega.setNivo_kiseonika(100);
        SessionPseudoClock clock = ksession.getSessionClock();
        Dijagnoza dijagnoza = new Dijagnoza();
        Bolest bolest = new Bolest();
        bolest.setNazivBolesti("hronicna bubrezna bolest");
        dijagnoza.setBolest(bolest);
        pacijentNega.setDijagnoza(dijagnoza);

        ksession.insert(pacijentNega);

        for (int index = 0; index < 10; index++) {
            MokrenjeEvent mokrenjeEvent = new MokrenjeEvent(jmbg, 10);
            ksession.insert(mokrenjeEvent);
            clock.advanceTime(1, TimeUnit.HOURS);
            int ruleCount = ksession.fireAllRules();
            assertThat(ruleCount, equalTo(0));
        }

        for (int index = 0; index < 15; index++) {
            OtkucajSrcaEvent otkucajSrcaEvent = new OtkucajSrcaEvent(jmbg);
            ksession.insert(otkucajSrcaEvent);
            clock.advanceTime(1, TimeUnit.MICROSECONDS);
        }

        int ruleCount = ksession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(DijalizaEvent.class));
        assertThat(newEvents.size(), equalTo(1));
    }
}
