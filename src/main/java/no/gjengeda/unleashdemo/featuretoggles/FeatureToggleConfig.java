package no.gjengeda.unleashdemo.featuretoggles;

import no.gjengeda.unleashdemo.UnleashDemoApplication;
import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.util.UnleashConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class
FeatureToggleConfig {

    @Bean
    public Unleash initializeUnleash(ByEnvironmentStrategy byEnvironmentStrategy,
                                     ByDepartmentStrategy byDepartmentStrategy,
                                     @Value("${unleash-demo.unleash.uri}") URI uri,
                                     // Token is read from environment
                                     @Value("${unleash-token}") String token) {
        UnleashConfig config = UnleashConfig.builder()
                .appName(UnleashDemoApplication.APP_NAME)
                .instanceId(UnleashDemoApplication.APP_NAME + byEnvironmentStrategy.getEnvironment())
                .environment(byEnvironmentStrategy.getEnvironment())
                .unleashAPI(uri)
                .customHttpHeader("Authorization", token)
                .build();

        return new DefaultUnleash(
                config,
                byEnvironmentStrategy,
                byDepartmentStrategy
        );
    }

//    @Bean
//    @Profile("local")
//    public Unleash unleashMock() {
//        FakeUnleash fakeUnleash = new FakeUnleash();
//        fakeUnleash.enableAll();
//        return fakeUnleash;
//    }
}
