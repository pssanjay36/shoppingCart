package com.example.shoppingCart.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource("rules/discountRules.drl"));
        KieBuilder kb = ks.newKieBuilder(kfs).buildAll();
        KieRepository kr = ks.getRepository();
        KieModule km = kb.getKieModule();
        return ks.newKieContainer(km.getReleaseId());
    }
}

