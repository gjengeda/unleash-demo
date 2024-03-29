package no.gjengeda.unleashdemo.featuretoggles;


import no.finn.unleash.strategy.Strategy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@Component
public class ByEnvironmentStrategy implements Strategy {

    private final String environment;
    public static final List<String> MILJOER = Arrays.asList("local", "dev", "prod");

    public ByEnvironmentStrategy(Environment environment) {
        this.environment = Stream.of(environment.getActiveProfiles()).filter(MILJOER::contains).findFirst().orElse("local");
    }

    @Override
    public String getName() {
        return "byEnvironment";
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters) {
        return Optional.ofNullable(parameters)
                .map(map -> map.get("miljø"))
                .map(env -> asList(env.split(",")).contains(environment))
                .orElse(false);
    }

    String getEnvironment() {
        return environment;
    }
}
