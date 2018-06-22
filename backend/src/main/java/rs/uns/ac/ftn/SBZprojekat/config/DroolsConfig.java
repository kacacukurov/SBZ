package rs.uns.ac.ftn.SBZprojekat.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.enterprise.context.ApplicationScoped;

@Configuration
public class DroolsConfig {

    @Autowired
    private KieContainer kieContainer;

    @Bean
    @ApplicationScoped
    public KieContainer kieContainer() {

        KieServices kieServices  = KieServices.Factory.get();
        KieContainer kContainer = kieServices.newKieContainer(kieServices.newReleaseId(
                "drools-spring-v2", "drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = kieServices.newKieScanner(kContainer);
        kScanner.start(10_000);

        return kContainer;
    }


    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KieSession kieSession() {
        return kieContainer.newKieSession("ExampleSession");
    }
}
