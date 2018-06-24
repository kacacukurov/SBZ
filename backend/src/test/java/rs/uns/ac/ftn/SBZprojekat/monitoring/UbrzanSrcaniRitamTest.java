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
import rs.uns.ac.ftn.SBZprojekat.model.events.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UbrzanSrcaniRitamTest {

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
        bolest.setNazivBolesti("neka bolest");
        dijagnoza.setBolest(bolest);
        pacijentNega.setDijagnoza(dijagnoza);

        ksession.insert(pacijentNega);

        for (int index = 0; index < 23; index++) {
            OtkucajSrcaEvent otkucajSrcaEvent = new OtkucajSrcaEvent(jmbg);
            ksession.insert(otkucajSrcaEvent);

            clock.advanceTime(1, TimeUnit.SECONDS);

            int ruleCount = ksession.fireAllRules();
            assertThat(ruleCount, equalTo(0));
        }

        clock.advanceTime(1, TimeUnit.SECONDS);
        for (int index = 0; index < 40; index++) {
            OtkucajSrcaEvent otkucajSrcaEvent = new OtkucajSrcaEvent(jmbg);
            ksession.insert(otkucajSrcaEvent);
        }

        clock.advanceTime(9, TimeUnit.SECONDS);
        int ruleCount = ksession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(UbrzanSrcaniRitamEvent.class));
        assertThat(newEvents.size(), equalTo(1));
    }
}
